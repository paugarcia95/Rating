package com.example.pau.drawer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pau.drawer.R;

/**
 * Created by Pau on 29/01/2015.
 */
public class BasicFragment extends Fragment {
    boolean b;

    public BasicFragment(){}

    public BasicFragment(boolean b) {
        this.b = b;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (b) return inflater.inflate(R.layout.basic_fragment, container, false);
        else return inflater.inflate(R.layout.basic_fragment2, container, false);
    }
}
