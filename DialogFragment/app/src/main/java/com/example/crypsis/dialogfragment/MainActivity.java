package com.example.crypsis.dialogfragment;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements mydialog.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDialog(View v)
    {
        FragmentManager manager=getFragmentManager();
        mydialog m=new mydialog();
        m.show(manager,"My Dialog");
    }

    @Override
    public void onDialogMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
