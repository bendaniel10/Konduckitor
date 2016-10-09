package com.bentechapps.konduckitor.view.animation;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;

/**
 * Created by Daniel on 5/14/2015.
 */
public class AnimationFactory {

    public static AnimationSet newWobbleAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(-10,10, 50, 50);
        rotateAnimation.setRepeatCount(5);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(150);
        set.addAnimation(rotateAnimation);
        return set;
    }

    public static AnimationSet newRotateAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setDuration(100);
        set.addAnimation(rotateAnimation);
        return set;
    }
    public static AnimationSet newRotateAnimation(int repeatCount) {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(repeatCount - 1);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setDuration(500);
        set.addAnimation(rotateAnimation);
        return set;
    }

}
