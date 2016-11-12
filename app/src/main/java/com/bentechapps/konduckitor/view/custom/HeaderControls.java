package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentech.android.appcommons.activity.AppCommonsActivity;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.ShopItemFragment;

/**
 * Created by Daniel on 5/10/2015.
 */
public class HeaderControls extends RelativeLayout implements View.OnClickListener {
    private final TextView title;
    private ImageButton previousButton;
    private ImageButton shopButton;
    private Button coinButton;
    FragmentManager fragmentManager;


    public HeaderControls(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header_controls, this, true);
        previousButton = (ImageButton) findViewById(R.id.previous_button);
        shopButton = (ImageButton) findViewById(R.id.shop_button);
        coinButton = (Button) findViewById(R.id.coin_button);
        title = (TextView) findViewById(R.id.title);
        previousButton.setOnClickListener(this);
        shopButton.setOnClickListener(this);
        coinButton.setOnClickListener(this);

    }


    public HeaderControls setTitle(String title) {
        this.title.setText(title);
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous_button:
                handlePrevious();
                break;
            case R.id.coin_button:
                handleCoin();
                break;
            case R.id.shop_button:
                handleShop();
                break;
        }
    }

    private void handleShop() {
        ShopItemFragment shopItemFragment = new ShopItemFragment();
        ((MainActivity) getContext()).switchFragmentsAddToBackStack(R.id.fragment_container, shopItemFragment);
    }

    private void handleCoin() {

    }

    private void handlePrevious() {
        ((AppCommonsActivity) getContext()).onBackPressed();
    }
}
