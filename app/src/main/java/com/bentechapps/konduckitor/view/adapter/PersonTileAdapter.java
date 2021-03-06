package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayPersonTileData;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FiftiesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FivesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.HundredsDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TensDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TwentiesDenominationUnit;
import com.bentechapps.konduckitor.model.difficulty.Difficulty;
import com.bentechapps.konduckitor.model.person.Person;
import com.bentechapps.konduckitor.model.person.impl.FemalePerson;
import com.bentechapps.konduckitor.model.person.impl.MalePerson;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by BenTech on 2/1/2015.
 */
public class PersonTileAdapter extends BaseAdapter {
    private final Random timeRandom;
    private final ApplicationData appData;
    protected Context mContext;
    protected GamePlayPersonTile[] personTiles = new GamePlayPersonTile[14];
    private Random paymentRandom = new Random();
    private DenominationUnit toPay, with;
    private static int EXIT_TIME;
    private List<FemalePerson> uniqueFemales;
    private List<MalePerson> uniqueMales;
    protected Set<Person> currentPassengers;
    private Iterator<MalePerson> uniqueMalesIterator;
    private Iterator<FemalePerson> uniqueFemalesIterator;

    public PersonTileAdapter(Context c) {
        mContext = c;
        uniqueFemales = FemalePerson.getFemalePersonList(c);
        uniqueFemalesIterator = uniqueFemales.iterator();
        uniqueMales = MalePerson.getMalePersonList(c);
        uniqueMalesIterator = uniqueMales.iterator();
        currentPassengers = new HashSet<>();
        timeRandom = new Random();
        appData = ApplicationData.getInstance(c);
        handleDifficultyCustomization();
        initPersonTiles();

    }

    protected void initPersonTiles() {
        for (int i = 0; i < 14; i++) {
            personTiles[i] = new GamePlayPersonTile(mContext, null);
            personTiles[i].setGamePlayPersonTileData(i % 2 == 0 ? getRandomMaleGamePlayPersonTileData() : getRandomFemaleGamePlayPersonTileData());
        }
    }

    private void handleDifficultyCustomization() {
        switch (appData.getDifficulty()) {
            case 0:
                EXIT_TIME = Difficulty.list().get(0).getMinimumExitTime();
                break;
            case 1:
                EXIT_TIME = Difficulty.list().get(1).getMinimumExitTime();
                break;
            case 2:
                EXIT_TIME = Difficulty.list().get(2).getMinimumExitTime();
                break;
            default:
                EXIT_TIME = Difficulty.list().get(1).getMinimumExitTime();
        }
    }

    public synchronized GamePlayPersonTileData getRandomFemaleGamePlayPersonTileData
            () {
        toPay = getRandomToPay();
        with = getRandomWith(toPay);
        Person person;
        do {
            person = newFemalePerson();
        } while (currentPassengers.add(person));


        return GamePlayPersonTileData.createPersonTileInfo(person, toPay,
                with, (short) (timeRandom.nextInt(EXIT_TIME) + EXIT_TIME));
    }

    public synchronized GamePlayPersonTileData getRandomMaleGamePlayPersonTileData() {
        toPay = getRandomToPay();
        with = getRandomWith(toPay);
        Person person;
        do {
            person = newMalePerson();
        } while (currentPassengers.add(person));


        return GamePlayPersonTileData.createPersonTileInfo(person, toPay,
                with, (short) (timeRandom.nextInt(EXIT_TIME) + EXIT_TIME));
    }


    private MalePerson newMalePerson() {
        if (uniqueMalesIterator.hasNext()) {
            return uniqueMalesIterator.next();

        } else {
            uniqueMalesIterator = uniqueMales.iterator();
            return uniqueMalesIterator.next();
        }

    }

    protected FemalePerson newFemalePerson() {
        if (uniqueFemalesIterator.hasNext()) {
            return uniqueFemalesIterator.next();

        } else {
            uniqueFemalesIterator = uniqueFemales.iterator();
            return uniqueFemalesIterator.next();
        }
    }

    private DenominationUnit getRandomToPay() {
        return getRandom(paymentRandom.nextInt(5));
    }

    private DenominationUnit getRandomWith(DenominationUnit toPay) {
        DenominationUnit d = getRandom(paymentRandom.nextInt(5));
        if (d.compareTo(toPay) < 0) {
            return getRandomWith(toPay);
        }
        return d;
    }

    private DenominationUnit getRandom(int i) {
        DenominationUnit d = new HundredsDenominationUnit();
        d.incrementCount((short) 1);
        switch (i) {
            case 0:
                d = new FivesDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 1:
                d = new TensDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 2:
                d = new TwentiesDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 3:
                d = new FiftiesDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 4:
                d = new HundredsDenominationUnit();
                d.incrementCount((short) 1);
                break;

        }
        return d;
    }


    public int getCount() {
        return 14;
    }

    public Object getItem(int position) {
        return personTiles[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        return personTiles[position];
    }

}
