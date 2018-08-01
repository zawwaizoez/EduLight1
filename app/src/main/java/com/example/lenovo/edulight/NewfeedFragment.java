package com.example.lenovo.edulight;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewfeedFragment extends Fragment {
    private FloatingActionButton user_post_upload_btn;
    public NewfeedFragment()
    {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed,container,false);
        return view;
    }
}
