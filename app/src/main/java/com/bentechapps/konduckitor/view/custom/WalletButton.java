package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;

import static android.view.View.*;

/**
 * Created by Daniel on 2/20/2015.
 */
public class WalletButton extends RelativeLayout implements OnClickListener {

    private final TextView amount;
    private final TextView count;
    DenominationUnit denominationUnit;

    public WalletButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.wallet_button, this, true);

        amount = (TextView) findViewById(R.id.money_amount);
        count = (TextView) findViewById(R.id.money_count);

    }

    public DenominationUnit getDenominationUnit() {
        return denominationUnit;
    }

    public void setDenominationUnit(DenominationUnit denominationUnit) {
        this.denominationUnit = denominationUnit;
    }

    public void setText(String text) {
        String moneyAmount, moneyCount;
        moneyAmount = text.split(",")[0].trim();
        moneyCount = text.split(",")[1].trim();
        amount.setText(moneyAmount);
        count.setText(moneyCount);
    }


    @Override
    public void onClick(View v) {

    }
}
