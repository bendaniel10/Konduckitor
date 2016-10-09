package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.sound.Sound;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment {


    public CreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credits, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TextView) getView().findViewById(R.id.createdBy)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) getView().findViewById(R.id.textView14)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) getView().findViewById(R.id.textView12)).setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.playApplauseSfx();
    }
}
