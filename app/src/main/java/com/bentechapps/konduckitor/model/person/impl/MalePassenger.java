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
// * Created by BenTech on 2/1/2015.
// * * Deprecated see PassengerFactory
// */
//@Deprecated
//public abstract class MalePassenger implements Passenger {
//
//    private final static int[] maleDrawables = {R.drawable.male_one, R.drawable.male_two, R.drawable.male_three, R.drawable.male_four,
//            R.drawable.male_five, R.drawable.male_six, R.drawable.male_seven, R.drawable.male_eight, R.drawable.male_nine, R.drawable.male_ten,
//            R.drawable.male_eleven, R.drawable.male_twelve, R.drawable.male_thirteen, R.drawable.male_fourteen, R.drawable.male_fifteen, R.drawable.male_sixteen,
//            R.drawable.male_seventeen, R.drawable.male_eighteen, R.drawable.male_nineteen};
//
//    private final static int[] maleDrawablesImpatient = {R.drawable.male_one_impatient, R.drawable.male_two_impatient, R.drawable.male_three_impatient, R.drawable.male_four_impatient,
//            R.drawable.male_five_impatient, R.drawable.male_six_impatient, R.drawable.male_seven, R.drawable.male_eight_impatient, R.drawable.male_nine_impatient, R.drawable.male_ten_impatient,
//            R.drawable.male_eleven_impatient, R.drawable.male_twelve_impatient, R.drawable.male_thirteen_impatient, R.drawable.male_fourteen_impatient, R.drawable.male_fifteen_impatient, R.drawable.male_sixteen_impatient,
//            R.drawable.male_seventeen_impatient, R.drawable.male_eighteen_impatient, R.drawable.male_nineteen_impatient};
//
//    private final static int[] maleDrawablesAngry = {R.drawable.male_one_angry, R.drawable.male_two_angry, R.drawable.male_three_angry, R.drawable.male_four_angry,
//            R.drawable.male_five_angry, R.drawable.male_six_angry, R.drawable.male_seven_angry, R.drawable.male_eight_angry, R.drawable.male_nine_angry, R.drawable.male_ten_angry,
//            R.drawable.male_eleven_angry, R.drawable.male_twelve_angry, R.drawable.male_thirteen_angry, R.drawable.male_fourteen_angry, R.drawable.male_fifteen_angry, R.drawable.male_sixteen_angry,
//            R.drawable.male_seventeen_angry, R.drawable.male_eighteen_angry, R.drawable.male_nineteen_angry};
//    private Random random = new Random();
//    private int drawableId;
//    private int drawableImpatientId;
//    private int drawableAngryId;
//
//    private final Context mContext;
//
//    private MalePassenger() {
//        mContext = null;
//    }
//
//    public MalePassenger(Context context) {
//        this.mContext = context;
//    }
//
//    @Override
//    public Drawable getImage() {
//        return mContext.getResources().getDrawable(getRandomMale());
//    }
//
//    private int getRandomMale() {
//        return maleDrawables[random.nextInt(7)];
//    }
//
//    @Override
//    public Sex getSex() {
//        return Sex.MALE;
//    }
//
//    public static List<MalePassenger> getMalePersonList(final Context context) {
//        MalePassenger person;
//        List<MalePassenger> set = new ArrayList<>();
//        for (int i = 0; i < maleDrawables.length; i++) {
//            final int finalI = i;
//            person = new MalePassenger(context) {
//                @Override
//                public Drawable getImage() {
//                    return context.getResources().getDrawable(maleDrawables[finalI]);
//                }
//
//                @Override
//                public Drawable getImageImpatient() {
//                    return context.getResources().getDrawable(maleDrawablesImpatient[finalI]);
//                }
//
//                @Override
//                public Drawable getImageAngry() {
//                    return context.getResources().getDrawable(maleDrawablesAngry[finalI]);
//                }
//            };
//            person.drawableId = maleDrawables[i];
//            person.drawableImpatientId = maleDrawablesImpatient[i];
//            person.drawableAngryId = maleDrawablesAngry[i];
//            set.add(person);
//        }
//        return set;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if(o == null)
//            return false;
//        if(!(o instanceof MalePassenger))
//            return false;
//        return (((MalePassenger) o).drawableId == this.drawableId);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode() + this.drawableId;
//    }
//}
