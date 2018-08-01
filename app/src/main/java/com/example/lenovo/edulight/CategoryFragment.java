package com.example.lenovo.edulight;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private CategoryRecyclerViewAdapter categoryAdapter;
    private View categoryView;
    private RecyclerView categoryRecyclerView;
    private List<Category> categoryList;
    private Typeface normalFont,thinFont;
    public CategoryFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        categoryView = inflater.inflate(R.layout.fragment_category,container,false);
        categoryRecyclerView = (RecyclerView)categoryView.findViewById(R.id.main_category_recycler_layout);
        categoryAdapter = new CategoryRecyclerViewAdapter(getActivity(),categoryList);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryRecyclerView.setAdapter(categoryAdapter);
        return categoryView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int [] subjectImage = new int[]{
                R.drawable.category_database_icon, R.drawable.category_cloud_computing_icon,
                R.drawable.category_artficial_intelligence_icon, R.drawable.category_network_icon,
                R.drawable.category_data_analysis_icon, R.drawable.category_coding_icon,
                R.drawable.category_critography_icon, R.drawable.category_maching_learning_icon,
                R.drawable.category_bussiness_strategy_icon, R.drawable.category_data_flow_analysis_icon,
                R.drawable.category_data_security_icon, R.drawable.category_graphic_design_icon,
                R.drawable.category_software_inspection_icon
        };
        categoryList = new ArrayList<Category>();
        categoryList.add(new Category("Learn Programming","Daw Hnin Aye Thant",subjectImage[5]));
        categoryList.add(new Category("Crytography","Daw Shwe Thinzar Aung",subjectImage[6]));
        categoryList.add(new Category("Bussiness Stradegy","Daw Phyu Tar",subjectImage[8]));
        categoryList.add(new Category("Machine Learning","Daw Yi Yi Myint",subjectImage[7]));
        categoryList.add(new Category("Data Flow Analysis","Daw Yi Yi Htoon",subjectImage[9]));
        categoryList.add(new Category("Data Security","Daw May Htet Htet Hlaing",subjectImage[10]));
        categoryList.add(new Category("Graphic Design","Daw Ei Zin Htun",subjectImage[11]));
        categoryList.add(new Category("Software Inspection","U Tin Hton Naing",subjectImage[12]));
        categoryList.add(new Category("Database Management System","U Tin Myint Naing",subjectImage[0]));
        categoryList.add(new Category("Cloud Computing","Daw Naw Naw",subjectImage[1]));
        categoryList.add(new Category("Artificial Intelligence","Daw Aye Aye Kyaw",subjectImage[2]));
        categoryList.add(new Category("Neworking(Cisco)","Daw Cho Thwe Aung",subjectImage[3]));
        categoryList.add(new Category("Big Data Analysis","Daw Tin Tin Oo",subjectImage[4]));
    }
}
