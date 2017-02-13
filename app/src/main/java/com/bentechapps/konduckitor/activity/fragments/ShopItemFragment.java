package com.bentechapps.konduckitor.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.view.adapter.ShopItemsAdapter;

public class ShopItemFragment extends ListFragment {

    private TextView reputationTextView;
    private ApplicationData appData;

    public ShopItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appData = ApplicationData.getInstance();
        reputationTextView = (TextView) getView().findViewById(R.id.reputationTextView);
        reputationTextView.setText(String.valueOf(appData.getReputation()));
        setListAdapter(new ShopItemsAdapter(getActivity(), reputationTextView));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.shop_item_fragment,  container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

}
