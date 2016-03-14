package com.example.crypsis.dialogfragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class mydialog extends DialogFragment implements View.OnClickListener {

    Button yes, no;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator=(Communicator) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog, null);
        yes = (Button) view.findViewById(R.id.button3);
        no = (Button) view.findViewById(R.id.button2);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
      setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
           communicator.onDialogMessage("yes was clicked");

            dismiss();

        }
        else {
            communicator.onDialogMessage("no was clicked");
            dismiss();
        }
    }

    interface Communicator
    {public void onDialogMessage(String message);}

}
