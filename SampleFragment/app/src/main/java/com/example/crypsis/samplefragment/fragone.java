package com.example.crypsis.samplefragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class fragone extends Fragment {
    EditText e;
    Button b;
    avi avi1;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fone, container, false);
        e = (EditText) view.findViewById(R.id.myedit);
        b = (Button) view.findViewById(R.id.mybutton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = e.getText().toString();
                avi1.setValue(str);
            }


        });

        return view;

    }

    public interface avi {
        public void setValue(String name);
    }

    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {
            avi1 = (avi) activity;
        } catch (Exception e) {
        }

    }
}