//package com.bentechapps.konduckitor.model.person.impl;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//
//import com.bentechapps.konduckitor.R;
//import com.bentechapps.konduckitor.model.person.Passenger;
//import com.bentechapps.konduckitor.model.person.Sex;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * Created by Daniel on 2/6/2015.
// * Deprecated see PassengerFactory
// */
//@Deprecated
//public abstract class FemalePassenger implements Passenger {
//
//    private final Context mContext;
//    private final static int[] femaleDrawables = {R.drawable.female_one, R.drawable.female_two, R.drawable.female_three, R.drawable.female_four,
//            R.drawable.female_five, R.drawable.female_six, R.drawable.female_seven, R.drawable.female_eight, R.drawable.female_nine, R.drawable.female_ten,
//            R.drawable.female_eleven, R.drawable.female_twelve, R.drawable.female_thirteen, R.drawable.female_fourteen, R.drawable.female_fifteen,
//            R.drawable.female_sixteen};
//
//    private final static int[] femaleDrawablesImpatient = {R.drawable.female_one_impatient, R.drawable.female_two_impatient, R.drawable.female_three_impatient, R.drawable.female_four_impatient,
//            R.drawable.female_five_impatient, R.drawable.female_six_impatient, R.drawable.female_seven_impatient, R.drawable.female_eight_impatient, R.drawable.female_nine_impatient, R.drawable.female_ten_impatient,
//            R.drawable.female_eleven_impatient, R.drawable.female_twelve_impatient, R.drawable.female_thirteen_impatient, R.drawable.female_fourteen_impatient, R.drawable.female_fifteen_impatient,
//            R.drawable.female_sixteen_impatient};
//    private final static int[] femaleDrawablesAngry = {R.drawable.female_one_angry, R.drawable.female_two_angry, R.drawable.female_three_angry, R.drawable.female_four_angry,
//            R.drawable.female_five_angry, R.drawable.female_six_angry, R.drawable.female_seven_angry, R.drawable.female_eight_angry, R.drawable.female_nine_angry, R.drawable.female_ten_angry,
//            R.drawable.female_eleven_angry, R.drawable.female_twelve_angry, R.drawable.female_thirteen_angry, R.drawable.female_fourteen_angry, R.drawable.female_fifteen_angry,
//            R.drawable.female_sixteen_angry};
//
//    private int drawableId;
//    private int drawableImpatientId;
//    private int drawableAngryId;
//
//    private Random random = new Random();
//
//    private FemalePassenger() {
//        mContext = null;
//    }
//
//    public FemalePassenger(Context context) {
//        this.mContext = context;
//    }
//
//    private int getRandomFemale() {
//        return femaleDrawables[random.nextInt(7)];
//    }
//
//    @Override
//    public Sex getSex() {
//        return Sex.FEMALE;
//    }
//
//    public static List<FemalePassenger> getFemalePersonList(final Context context) {
//        FemalePassenger person;
//        List<FemalePassenger> set = new ArrayList<>();
//        for (int i = 0; i < femaleDrawables.length; i++) {
//            final int finalI = i;
//            person = new FemalePassenger() {
//                @Override
//                public Drawable getImage() {
//                    return context.getResources().getDrawable(femaleDrawables[finalI]);
//                }
//
//                @Override
//                public Drawable getImageImpatient() {
//                    return context.getResources().getDrawable(femaleDrawablesImpatient[finalI]);
//                }
//
//                @Override
//                public Drawable getImageAngry() {
//                    return context.getResources().getDrawable(femaleDrawablesAngry[finalI]);
//                }
//            };
//
//            person.drawableId = femaleDrawables[i];
//            person.drawableImpatientId = femaleDrawablesImpatient[i];
//            person.drawableAngryId = femaleDrawablesAngry[i];
//            set.add(person);
//        }
//        return set;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if(o == null)
//            return false;
//        if(!(o instanceof FemalePassenger))
//            return false;
//        return (((FemalePassenger) o).drawableId == this.drawableId);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode() + this.drawableId;
//    }
//}
