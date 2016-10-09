package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.shop.ShopItem;

/**
 * Created by BenTech on 2/9/2015.
 */
public class PreferenceSpinnerAdapter extends ArrayAdapter<ShopItem> {
    public PreferenceSpinnerAdapter(Context context, int resource, int textView, ShopItem[] objects) {
        super(context, resource, textView, objects);
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            view = View.inflate(getContext(), R.layout.shop_spinner_item, null);
        }

        ShopItem item = getItem(position);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView text = (TextView) view.findViewById(R.id.text);

        image.setImageDrawable(item.getImage());
        text.setText(new StringBuilder().append(item.getName()).append(" (").append(item.getHave()).append(")").toString());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
