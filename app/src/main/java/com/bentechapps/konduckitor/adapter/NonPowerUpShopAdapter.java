package com.bentechapps.konduckitor.adapter;

import android.app.Application;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.DrawableUtils;
import com.bentech.android.appcommons.utils.TextUtils;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.model.shop.NonPowerUpItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bendaniel on 25/02/2017.
 */
public class NonPowerUpShopAdapter extends RecyclerView.Adapter<NonPowerUpShopAdapter.CustomViewHolder> {

    private final Application context;
    private List<NonPowerUpItem> shopItems;
    private View.OnClickListener onListItemClicked;

    public NonPowerUpShopAdapter(List<NonPowerUpItem> shopItems, View.OnClickListener onListItemClicked) {
        this.shopItems = shopItems;
        this.onListItemClicked = onListItemClicked;
        this.context = AppCommons.getApplication();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.non_powerup_item, null);

        return new CustomViewHolder(view, onListItemClicked);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        NonPowerUpItem nonPowerUpItem = shopItems.get(position);

        TextUtils.setUxAmountText(holder.cost, String.valueOf(nonPowerUpItem.getCost()), Constants.CURRENCY_SYMBOL);
        TextUtils.setUxText(holder.description, context.getString(nonPowerUpItem.getDescriptionResId()));
        TextUtils.setUxText(holder.name, context.getString(nonPowerUpItem.getNameResId()));

        if (nonPowerUpItem.getHave() != NonPowerUpItem.UNLIMITED_HAVES) {
            TextUtils.setUxText(holder.have, String.format("%s: %s", context.getString(R.string.you_have), nonPowerUpItem.getHave()));
        } else {
            holder.have.setText("");
        }
        holder.image.setImageDrawable(DrawableUtils.getDrawable(context, nonPowerUpItem.getDrawableId()));


    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private final View.OnClickListener onClickListener;
        @BindView(R.id.tv_costs)
        TextView cost;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.imageView)
        ImageView image;
        @BindView(R.id.tv_have)
        TextView have;
        @BindView(R.id.tv_itemname)
        TextView name;

        public CustomViewHolder(View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            this.onClickListener = onClickListener;

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.b_buy)
        void buyAction() {
            onClickListener.onClick(this.itemView);
        }
    }
}
