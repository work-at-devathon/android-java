package com.example.crypsis.send;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlanetFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position=getArguments().getInt("position");
        String[] planets=getResources().getStringArray(R.array.planets_array);
        View v=inflater.inflate(R.layout.fragment_planet, container, false);
        TextView tv=(TextView)v.findViewById(R.id.text);
        tv.setText(planets[position]);
//        getActivity().getActionBar().setTitle(planets[position]);
        return v;
    }
}