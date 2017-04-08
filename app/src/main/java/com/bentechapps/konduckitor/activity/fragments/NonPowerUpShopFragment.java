package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.adapter.NonPowerUpShopAdapter;
import com.bentechapps.konduckitor.model.shop.NonPowerUpItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonPowerUpShopFragment extends AppCommonsFragment {

    @BindView(R.id.rv_non_powerup_recyclerview)
    RecyclerView nonPowerUpRecyclerView;
    private List<NonPowerUpItem> items;

    public NonPowerUpShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items = NonPowerUpItem.list();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_non_powerup_shop, container, false);
        ButterKnife.bind(this, rootView);

        nonPowerUpRecyclerView.setAdapter(new NonPowerUpShopAdapter(items, onListItemClick()));
        nonPowerUpRecyclerView.setLayoutManager(new LinearLayoutManager(appCommonsActivity));

        return rootView;

    }

    private View.OnClickListener onListItemClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedPosition = nonPowerUpRecyclerView.getChildAdapterPosition(v);
                items.get(selectedPosition).purchase();

            }
        };
    }

}
