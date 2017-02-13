package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentech.android.appcommons.utils.DrawableUtils;
import com.bentech.android.appcommons.utils.TextUtils;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.model.person.Passenger;
import com.bentechapps.konduckitor.model.person.PassengerState;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bendaniel on 04/01/2017.
 */

public class PassengersAdapter extends RecyclerView.Adapter<PassengersAdapter.CustomViewHolder> {

    private View.OnClickListener onListItemClicked;
    private Context context;
    private List<Passenger> passengers;


    public PassengersAdapter(View.OnClickListener onListItemClicked, Context context, List<Passenger> passengers) {
        this.onListItemClicked = onListItemClicked;
        this.context = context;
        this.passengers = passengers;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.passenger_grid_item, null);
        view.setOnClickListener(onListItemClicked);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Passenger selectedPassenger = passengers.get(position);

        holder.avatarImg.setImageDrawable(DrawableUtils.getDrawable(context, selectedPassenger.getImageBasedOnState()));
        if(selectedPassenger.getAmountWith().getValue() < 0) {
            TextUtils.setUxText(holder.moneyTxt, String.format("-%s%s", Constants.CURRENCY_SYMBOL, String.valueOf(Math.abs(selectedPassenger.getAmountWith().getValue()))));
        } else {
            TextUtils.setUxText(holder.moneyTxt, String.format("%s%s", Constants.CURRENCY_SYMBOL, String.valueOf(selectedPassenger.getAmountWith().getValue())));
        }


        if (selectedPassenger.getPassengerState() == PassengerState.IMPATIENT) {
            DrawableUtils.setBackgroundDrawable(holder.wrapper, new ColorDrawable(ContextCompat.getColor(context, R.color.light_yellow)));
        } else if (selectedPassenger.getPassengerState() == PassengerState.ANGRY) {
            DrawableUtils.setBackgroundDrawable(holder.wrapper, new ColorDrawable(ContextCompat.getColor(context, R.color.light_red)));
        } else {
            DrawableUtils.setBackgroundDrawable(holder.wrapper, null);
        }


        //state
        if (selectedPassenger.getAmountWith().getValue() > 0) {
            //passenger hasn't paid
            holder.stateImg.setImageDrawable(DrawableUtils.getDrawable(context, R.drawable.uncollected_money_status));
        } else if (selectedPassenger.getAmountWith().getValue() < 0) {
            //passenger is being owed
            holder.stateImg.setImageDrawable(DrawableUtils.getDrawable(context, R.drawable.owe_status));
        } else {
            holder.stateImg.setImageDrawable(DrawableUtils.getDrawable(context, R.drawable.settled_status));
        }

        if (selectedPassenger.isLowPatienceSignalled() && selectedPassenger.getLowPatienceSignalledCount() < 1) {
            YoYo.with(Techniques.Pulse).playOn(holder.avatarImg);
            selectedPassenger.setLowPatienceSignalledCount((byte) (selectedPassenger.getLowPatienceSignalledCount() + 1));
        }
        holder.progress.setProgress(calculateProgress(selectedPassenger));

    }

    private int calculateProgress(Passenger selectedPassenger) {
        return (int) (((double) Math.max(0, selectedPassenger.getTimeLeft()) / (double) selectedPassenger.getExitTime()) * 100);
    }

    @Override
    public int getItemCount() {
        return passengers.size();
    }

    public Passenger getItem(int position) {
        if (position < 0 || position > getItemCount() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return passengers.get(position);
    }

    public void update(int i, Passenger passenger) {
        passengers.set(i, passenger);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.stateImg)
        ImageView stateImg;
        @BindView(R.id.avatarImg)
        ImageView avatarImg;
        @BindView(R.id.moneyTxt)
        TextView moneyTxt;
        @BindView(R.id.wrapper)
        RelativeLayout wrapper;
        @BindView(R.id.progress)
        CircularProgressBar progress;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
