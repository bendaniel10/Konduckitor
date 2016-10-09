package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by Daniel on 2/20/2015.
 */
public class UserTileButton extends ImageButton {
    public UserTileButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(),
//                widthMeasureSpec);
//        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(),
//                heightMeasureSpec);
//        // Ensure this view is always square.
//        int min = Math.min(measuredHeight, measuredWidth);
//        setMeasuredDimension(min, min);
//    }
}
