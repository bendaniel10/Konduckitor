package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentech.android.appcommons.activity.AppCommonsActivity;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.StoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Daniel on 5/10/2015.
 */
public class HeaderControls extends RelativeLayout {
    @BindView(R.id.titleTxt)
    TextView titleTxt;
    @BindView(R.id.subTitleTxt)
    TextView subTitleTxt;


    public HeaderControls(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header_controls, this, true);
        ButterKnife.bind(this, this);

    }


    public HeaderControls setTitle(String title) {
        if (!isInEditMode()) {
            this.titleTxt.setText(title);
        }
        return this;
    }

    public HeaderControls setSubTitle(String subTitle) {
        if (!isInEditMode()) {
            this.subTitleTxt.setText(subTitle);
        }
        return this;
    }


    @OnClick(R.id.previous_button)
    void handlePrevious() {
        if (!isInEditMode()) {
            ((AppCommonsActivity) getContext()).onBackPressed();
        }

    }

    @OnClick(R.id.shop_button)
    void handleShop() {
        if (!isInEditMode()) {
            ((MainActivity) getContext()).switchFragmentsAddToBackStack(R.id.fragment_container, new StoreFragment());
        }
    }
}
