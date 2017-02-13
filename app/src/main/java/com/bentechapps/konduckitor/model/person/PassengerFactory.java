package com.bentechapps.konduckitor.model.person;

import android.content.Context;

import com.bentech.android.appcommons.AppCommons;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.level.Level;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.bentechapps.konduckitor.activity.fragments.GamePlayFragment.TARGET_FPS;

/**
 * Created by bendaniel on 05/01/2017.
 */

public class PassengerFactory {


    public static String MALE_MAP_KEY = "MALES";
    public static String FEMALE_MAP_KEY = "FEMALES";
    private static PassengerBuilder passengerBuilder;
    private static List<Passenger> malePassengers;
    private static List<Passenger> femalePassengers;

    private synchronized static PassengerBuilder builder(Level level) {
        if (passengerBuilder == null) {
            passengerBuilder = new PassengerBuilder(level);
            return passengerBuilder;
        }

        return passengerBuilder;
    }

    private static void recycleBuilder(Level level) {
        passengerBuilder = new PassengerBuilder(level);
    }

    public synchronized static List<Passenger> listPassengers(Level level) {

        buildPassengersMap(level);

        List<Passenger> passengers = new ArrayList<>();

        Iterator<Passenger> maleIterator = malePassengers.iterator();
        Iterator<Passenger> femaleIterator = femalePassengers.iterator();
        for (int i = 0; i < Constants.MAX_MALES + Constants.MAX_FEMALES; i++) {
            passengers.add(
                    i % 2 == 0 ? maleIterator.next() : femaleIterator.next()
            );
        }

        return passengers;
    }

    public synchronized static Map<String, List<Passenger>> buildPassengersMap(Level level) {

        malePassengers = new ArrayList<>(Constants.MAX_MALES);
        femalePassengers = new ArrayList<>(Constants.MAX_FEMALES);

        builder(level);

        malePassengers.addAll(passengerBuilder.withMaleCount(Constants.MAX_MALES).buildList());

        recycleBuilder(level);

        femalePassengers.addAll(passengerBuilder.withFemaleCount(Constants.MAX_FEMALES).buildList());

        //return as map
        HashMap<String, List<Passenger>> map = new HashMap<>();

        map.put(MALE_MAP_KEY, malePassengers);
        map.put(FEMALE_MAP_KEY, femalePassengers);

        return map;

    }

    private static class PassengerBuilder implements Serializable {
        private final Level level;
        private int maleCount;
        private int femaleCount;
        private Random random = new Random();

        public PassengerBuilder(Level level) {
            this.level = level;
        }

        public PassengerBuilder withMaleCount(int maleCount) {
            this.maleCount = maleCount;
            return this;
        }

        public PassengerBuilder withFemaleCount(int femaleCount) {
            this.femaleCount = femaleCount;
            return this;
        }

        public List<Passenger> buildList() {
            List<Passenger> passengers = new ArrayList<>(maleCount + femaleCount);

            Passenger aPassenger;
            Context context = AppCommons.getApplication();

            for (int i = 0; i < maleCount; i++) {
                aPassenger = createPassenger(context, true, i +1);
                passengers.add(aPassenger);
            }

            for (int i = 0; i < femaleCount; i++) {
                aPassenger = createPassenger(context, false, i + 1);
                passengers.add(aPassenger);
            }

            return passengers;
        }

        private Passenger createPassenger(Context context, boolean male, int count) {

            Passenger aPassenger = new Passenger();

            aPassenger.setAngryImage(getDrawableIdFromResourceId(context, String.format("%s_angry_%s", male ? "m" : "f", count)));
            aPassenger.setDefaultImage(getDrawableIdFromResourceId(context, String.format("%s_default_%s", male ? "m" : "f", count)));
            aPassenger.setSettledImage(getDrawableIdFromResourceId(context, String.format("%s_settled_%s", male ? "m" : "f", count)));
            aPassenger.setImpatientImage(getDrawableIdFromResourceId(context, String.format("%s_impatient_%s", male ? "m" : "f", count)));
            aPassenger.setExitTime((random.nextInt(Constants.EXIT_TIME - level.getLevelNumber()) + Constants.EXIT_TIME - level.getLevelNumber()) * TARGET_FPS);
            aPassenger.setTimeLeft(aPassenger.getExitTime());
            aPassenger.setMale(male);
            aPassenger.setAmountToPay(DenominationUnit.getRandomDenominationUnit());
            aPassenger.setAmountWith(DenominationUnit.getRandomDenominationUnit(aPassenger.getAmountToPay()));

            return aPassenger;
        }

        private int getDrawableIdFromResourceId(Context context, String resourceIdentifier) {
            return context.getResources().getIdentifier(resourceIdentifier, "drawable", context.getPackageName());
        }
    }

}
