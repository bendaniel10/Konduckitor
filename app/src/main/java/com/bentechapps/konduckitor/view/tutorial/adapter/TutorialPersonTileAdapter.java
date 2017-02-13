//package com.bentechapps.konduckitor.view.tutorial.adapter;
//
//import android.content.Context;
//
//import com.bentechapps.konduckitor.data.GamePlayPersonTileData;
//import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
//import com.bentechapps.konduckitor.model.denomination.unit.impl.FiftiesDenominationUnit;
//import com.bentechapps.konduckitor.model.denomination.unit.impl.HundredsDenominationUnit;
//import com.bentechapps.konduckitor.model.person.Passenger;
//import com.bentechapps.konduckitor.view.adapter.PersonTileAdapter;
//import com.bentechapps.konduckitor.view.tutorial.GamePlayTutorialPersonTile;
//
///**
// * Created by Daniel on 2/18/2015.
// */
//public class TutorialPersonTileAdapter extends PersonTileAdapter {
//    public TutorialPersonTileAdapter(Context c) {
//        super(c);
//    }
//
//    @Override
//    protected void initPersonTiles() {
//        for (int i = 0; i < 14; i++) {
//            if (i == 0) {//ensuring that the money of the tutorial passenger of interest will not be equal
//                DenominationUnit toPay = new FiftiesDenominationUnit();
//                toPay.incrementCount((short) 1);
//                DenominationUnit with = new HundredsDenominationUnit();
//                with.incrementCount(((short) 1));
//                Passenger passenger;
//                do {
//                    passenger = newFemalePerson();
//                } while (currentPassengers.add(passenger));
//
//                personTiles[i] = new GamePlayTutorialPersonTile(mContext, null);
//                personTiles[i].setGamePlayPersonTileData(GamePlayPersonTileData.createPersonTileInfo(passenger, toPay, with, (short) 60));
//                continue;
//            }
//            personTiles[i] = new GamePlayTutorialPersonTile(mContext, null);
//            personTiles[i].setGamePlayPersonTileData(i % 2 == 0 ? getRandomFemaleGamePlayPersonTileData() : getRandomMaleGamePlayPersonTileData());
//        }
//    }
//}
