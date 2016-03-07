package com.example.crypsis.tabdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTabs();
    }

    private void setupTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);


        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("first")

                .setTabListener(new SupportFragmentTabListener<FirstFragment>(R.id.flContainer, this,
                        "first", FirstFragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0c2354")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#0c2354")));




        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("second")

                .setTabListener(new SupportFragmentTabListener<SecondFragment>(R.id.flContainer, this,
                        "second", SecondFragment.class));

        actionBar.addTab(tab2);
        ActionBar.Tab tab3=actionBar.newTab().setText("third").setTabListener(new SupportFragmentTabListener<ThirdFragment>(R.id.flContainer, this,
                "first", ThirdFragment.class));
        actionBar.addTab(tab3);

    }

}