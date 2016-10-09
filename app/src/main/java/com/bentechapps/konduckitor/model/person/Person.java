package com.bentechapps.konduckitor.model.person;

import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;

/**
 * Created by BenTech on 1/31/2015.
 */
//<a href="http://www.freepik.com/free-vector/hand-drawn-face-icons_761764.htm">Designed by Freepik</a>
public interface Person {
    public Drawable getImage();
    public Drawable getImageImpatient();
    public Drawable getImageAngry();
    public Sex getSex();
}
