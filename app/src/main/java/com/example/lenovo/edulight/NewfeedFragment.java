package com.example.lenovo.edulight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewfeedFragment extends Fragment {
    public NewfeedFragment()
    {

    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_feed,container,false);
    }
}
