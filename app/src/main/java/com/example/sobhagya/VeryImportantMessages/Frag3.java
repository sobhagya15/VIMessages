package com.example.sobhagya.VeryImportantMessages;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NMZ on 06-07-2016.
 */
public class Frag3 extends Fragment {
    View rootv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootv=inflater.inflate(R.layout.frag3,container,false);
        getActivity().finish();
        return rootv;
    }
}
