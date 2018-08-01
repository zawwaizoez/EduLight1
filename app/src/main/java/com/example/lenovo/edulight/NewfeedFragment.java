package com.example.lenovo.edulight;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewfeedFragment extends Fragment {
    private FloatingActionButton new_feed_upload_post_btn;
    public NewfeedFragment()
    {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed,container,false);
        new_feed_upload_post_btn = (FloatingActionButton)view.findViewById(R.id.new_feed_upload_post_btn);
        new_feed_upload_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PostUploadActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
