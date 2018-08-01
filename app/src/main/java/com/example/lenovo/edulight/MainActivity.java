package com.example.lenovo.edulight;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar main_content_toolbar;
    private TabLayout main_tab_layout;
    private ViewPager main_fragment_viewPager;
    private TextView main_layout_toolbar_title;
    private FloatingActionButton user_upload_post_btn;
    private Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpLayout();
    }


    private void setUpLayout() {
        main_content_toolbar = (Toolbar) findViewById(R.id.main_content_toolbar);
        main_layout_toolbar_title = (TextView) main_content_toolbar.findViewById(R.id.main_content_toolbar_title);
        main_layout_toolbar_title.setText("EduLite");
        main_layout_toolbar_title.setTypeface(Typeface.createFromAsset(getAssets(), "oxygen.regular.ttf"));

        setSupportActionBar(main_content_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        main_fragment_viewPager = (ViewPager) findViewById(R.id.main_content_fragment_ViewPager);
        setupViewPager(main_fragment_viewPager);

        main_tab_layout = (TabLayout) findViewById(R.id.main_content_tab_layout);
        main_tab_layout.setupWithViewPager(main_fragment_viewPager);

        addMainLayoutTabIcons();

    }

    private void addMainLayoutTabIcons() {
        int icons[] = new int[]{
                R.drawable.ic_newfeed_logo_svg_24dp,
                R.drawable.ic_category_logo_svg_24dp,
                R.drawable.ic_forum_logo_svg_24dp,
                R.drawable.ic_user_profile_logo_svg_24dp

        };
        main_tab_layout.getTabAt(0).setIcon(icons[0]);
        main_tab_layout.getTabAt(1).setIcon(icons[1]);
        main_tab_layout.getTabAt(2).setIcon(icons[2]);
        main_tab_layout.getTabAt(3).setIcon(icons[3]);
        main_tab_layout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        main_tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.colorTabSelected), PorterDuff.Mode.SRC_IN);
        main_tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.colorTabSelected), PorterDuff.Mode.SRC_IN);
        main_tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.colorTabSelected), PorterDuff.Mode.SRC_IN);
        main_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorTabSelected), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void applyFontForMainToolbar(Activity context) {
        Toolbar toolbar = (Toolbar) context.findViewById(R.id.main_content_toolbar);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (textView.getText().equals(toolbar.getTitle())) {
                    applyFont(textView, context);
                    break;
                }
            }
        }
    }

    private static void applyFont(TextView textView, Activity context) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "oxygen.regular.ttf"));
    }

    private void setupViewPager(ViewPager pager) {
        viewPagerAdaper adapter = new viewPagerAdaper(getSupportFragmentManager());
        adapter.addFragment(new NewfeedFragment(), "Newfeed");
        adapter.addFragment(new CategoryFragment(), "Collection");
        adapter.addFragment(new ForumFragment(), "Resource");
        adapter.addFragment(new ProfileFragment(), "Discussion");
        main_fragment_viewPager.setAdapter(adapter);
    }


    class viewPagerAdaper extends FragmentPagerAdapter {
        private final List<Fragment> myFragmentList = new ArrayList<>();
        private final List<String> myFragmentListTitle = new ArrayList<>();

        public viewPagerAdaper(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return myFragmentList.get(position);
        }

        public int getCount() {
            return myFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            myFragmentList.add(fragment);
            myFragmentListTitle.add(title);
        }

        public CharSequence getPageTitle(int position) {

            return null;
        }
    }















    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_show_case_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actvity_main_show_case_view) {
                presentShowcaseSequence();

        }
        return true;

    }
    private void presentShowcaseSequence() {

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {

            }
        });

        sequence.setConfig(config);

        sequence.addSequenceItem(tabOne, "This section can help teacher to share their information and show the content of information for student", "Next");

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(tabTwo)
                        .setDismissText("Next")
                        .setContentText("This section provide the feature to show every number of posts with separated category and so students can easily read the information")
                        .withCircleShape(true)
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(tabThree)
                        .setDismissText("Next")
                        .setContentText("This section can help students to share the information that they teached within the class and the others can make a note easily")
                        .withCircleShape(true)
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(tabFour)
                        .setDismissText("Next")
                        .setContentText("This section provide the feature to share a lot of resources amoung students and this data can help them to practise for later")
                        .withCircleShape(true)
                        .build()
        );

        *//*sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(uploadActionBtn)
                        .setDismissText("Next")
                        .setContentText("This button is for teaher to upload the information that they want to share for students to get resources")
                        .withCircleShape(true)
                        .build()
        );*//*
        sequence.start();

    }
    */
}
