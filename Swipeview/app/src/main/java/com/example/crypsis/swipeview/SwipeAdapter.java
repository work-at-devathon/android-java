package com.example.crypsis.swipeview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by crypsis on 14/3/16.
 */
public class SwipeAdapter extends FragmentPagerAdapter {

    SwipeAdapter(android.support.v4.app.FragmentManager fragmentManager)
    {super(fragmentManager);}
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new PageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("count",position+1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
