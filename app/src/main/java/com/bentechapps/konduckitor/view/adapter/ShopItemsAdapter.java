package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.model.shop.impl.CalmDown;
import com.bentechapps.konduckitor.model.shop.impl.RegenerateHealth;
import com.bentechapps.konduckitor.sound.Sound;

/**
 * Created by BenTech on 2/8/2015.
 */
public class ShopItemsAdapter extends BaseAdapter {

    private TextView reputationTextView;
    private ApplicationData appData;
    private Context context;
    ShopItem[] shopItems;
    public ShopItemsAdapter(Context context, TextView reputationTextView) {
        this.context = context;
        this.reputationTextView = reputationTextView;
        appData = ApplicationData.getInstance(context);
        initShopItems();
    }

    private void initShopItems() {
        shopItems = (ShopItem[]) ShopItem.list(context).toArray();
    }

    private ShopItemsAdapter() {
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
        View view ;
        ShopItem shopItem = (ShopItem) getItem(position);
        if(convertView == null) {
            view = View.inflate(context, R.layout.shop_item, null);
        } else  {
            view = convertView;
        }

        if(position % 2 == 0) {
            view.setBackgroundResource(R.color.light_yellow);
        } else {
            view.setBackgroundResource(R.color.white_alice_blue);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView description = (TextView) view.findViewById(R.id.description);
        TextView have = (TextView) view.findViewById(R.id.have);
        TextView cost = (TextView) view.findViewById(R.id.costs);
        TextView name = (TextView) view.findViewById(R.id.name);
        Button buy = (Button) view.findViewById(R.id.buy);
        if(appData.getHighScore() < shopItem.getMinimumAllowedHighScore()) {
            buy.setText(new StringBuilder().append("score ").append(shopItem.getMinimumAllowedHighScore()).append(" required"));
            buy.setTextColor(Color.RED);
        } else {
            buy.setText("BUY");
            buy.setTextColor(Color.GREEN);
            buy.setOnClickListener(new BuyOnClickListener(shopItem, have));
        }

        imageView.setImageDrawable(shopItem.getImage());
        description.setText(String.valueOf(shopItem.getDescription()));
        have.setText(String.valueOf(shopItem.getHave()));
        cost.setText(String.valueOf(shopItem.getCost()));
        name.setText(String.valueOf(shopItem.getName()));



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
            if(appData.getReputation() >= shopItem.getCost()) {
                Sound.playButtonClickSfx();
                appData.decrementReputation(shopItem.getCost());
                shopItem.incrementHave(1);
                reputationTextView.setText(String.valueOf(appData.getReputation()));
                have.setText(String.valueOf(shopItem.getHave()));
            } else {
                Toast.makeText(context, "Not enough reputation", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
