package com.example.crypsis.samplefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class fragtwo extends Fragment {TextView t;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ftwo,container,false);
        t=(TextView)view.findViewById(R.id.displaystring);
        t.setVisibility(view.GONE);
        return view;
    }
    public void setname(String s){
t.setText("Welcome "+s);t.setVisibility(getView().VISIBLE);
    }
}
