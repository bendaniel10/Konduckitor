package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;

/**
 * Created by BenTech on 2/8/2015.
 */
public class ShopItemsAdapter extends BaseAdapter {

    ShopItem[] shopItems;
    private TextView reputationTextView;
    private ApplicationData appData;
    private Context context;

    public ShopItemsAdapter(Context context, TextView reputationTextView) {
        this.context = context;
        this.reputationTextView = reputationTextView;
        appData = ApplicationData.getInstance();
        initShopItems();
    }

    private ShopItemsAdapter() {
    }

    private void initShopItems() {
        shopItems = (ShopItem[]) ShopItem.list(context).toArray();
    }

    @Override
    public int getCount() {
        return shopItems.length;
    }

    @Override
    public Object getItem(int position) {
        return shopItems[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ShopItem shopItem = (ShopItem) getItem(position);
        if (convertView == null) {
            view = View.inflate(context, R.layout.shop_item, null);
        } else {
            view = convertView;
        }

        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.light_yellow);
        } else {
            view.setBackgroundResource(R.color.white_alice_blue);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView description = (TextView) view.findViewById(R.id.description);
        TextView have = (TextView) view.findViewById(R.id.have);
        TextView cost = (TextView) view.findViewById(R.id.costs);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView level = (TextView) view.findViewById(R.id.level);
        Button buy = (Button) view.findViewById(R.id.buy);
        Button upgrade = (Button) view.findViewById(R.id.upgrade);

//        if(appData.getHighScore() < shopItem.getMinimumAllowedHighScore()) {
//            buy.setText(new StringBuilder().append("score ").append(shopItem.getMinimumAllowedHighScore()).append(" required"));
//            buy.setTextColor(Color.RED);
//        } else {
//            buy.setText("BUY");
//            buy.setTextColor(Color.GREEN);
//            buy.setOnClickListener(new BuyOnClickListener(shopItem, have));
//        }

        buy.setText(R.string.buy);
        buy.setTextColor(Color.GREEN);
        buy.setOnClickListener(new BuyOnClickListener(shopItem, have));


        if (shopItem.getUpgradeLevel() >= Constants.MAX_POWER_UP_UPGRADE_LEVEL) {
            upgrade.setVisibility(View.GONE);
        } else {
            upgrade.setVisibility(View.VISIBLE);
            upgrade.setOnClickListener(new UpgradeOnclickListener(shopItem, have, level, upgrade));
        }

        imageView.setImageDrawable(shopItem.getImage());
        description.setText(String.valueOf(shopItem.getDescription()));
        have.setText(String.valueOf(shopItem.getHave()));
        cost.setText(String.valueOf(shopItem.getCost()));
        name.setText(String.valueOf(shopItem.getName()));
        level.setText(String.format("%s / %s", shopItem.getUpgradeLevel(), Constants.MAX_POWER_UP_UPGRADE_LEVEL));

        return view;
    }

    private class BuyOnClickListener implements View.OnClickListener {

        private final ShopItem shopItem;
        private final TextView have;

        public BuyOnClickListener(ShopItem shopItem, TextView have) {
            this.shopItem = shopItem;
            this.have = have;
        }

        @Override
        public void onClick(View v) {
            if (appData.getReputation() >= shopItem.getCost()) {
                Sound.playButtonClickSfx();
                appData.decrementReputation(shopItem.getCost());
                shopItem.incrementHave(1);
                appData.savePreference();
                reputationTextView.setText(String.valueOf(appData.getReputation()));
                have.setText(String.valueOf(shopItem.getHave()));
            } else {
                Toast.makeText(context, R.string.you_do_not_have_enough_money, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UpgradeOnclickListener implements View.OnClickListener {
        private final ShopItem shopItem;
        private final TextView have;
        private TextView level;
        private Button upgradeButton;

        public UpgradeOnclickListener(ShopItem shopItem, TextView have, TextView level, Button upgradeButton) {
            this.shopItem = shopItem;
            this.have = have;
            this.level = level;
            this.upgradeButton = upgradeButton;
        }

        @Override
        public void onClick(View v) {
            int upgradeCost = shopItem.getCost() * (shopItem.getUpgradeLevel() + 1);

            if(shopItem.getUpgradeLevel() == Constants.MAX_POWER_UP_UPGRADE_LEVEL) {
                Toast.makeText(context, R.string.maximum_upgrade_reached, Toast.LENGTH_SHORT).show();
                return;
            }

            if (appData.getReputation() >= upgradeCost) {
                Sound.playButtonClickSfx();

                appData.decrementReputation(upgradeCost);
                shopItem.incrementUpgradeLevel(1);
                appData.savePreference();

                reputationTextView.setText(String.valueOf(appData.getReputation()));

                notifyDataSetChanged();
            } else {
                Toast.makeText(context, R.string.you_do_not_have_enough_money, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
