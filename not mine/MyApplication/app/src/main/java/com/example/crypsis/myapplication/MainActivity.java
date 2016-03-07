package com.example.crypsis.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            b1 = (Button)findViewById(R.id.button1);
            b2 = (Button)findViewById(R.id.button2);
            b3 = (Button)findViewById(R.id.button3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    public void onClick(View v)
    {

        Intent intent = null;
        Intent chooser = null;
        if(v.getId() == R.id.button1){

            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:19.076,72.8777"));
            chooser = intent.createChooser(intent,"launch maps");
            startActivity(chooser);
        }
        if(v.getId() == R.id.button2){

            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"rajendra@crypsis.net","gopal@crypsis.net"};
            intent.putExtra(intent.EXTRA_EMAIL,to);
            intent.putExtra(intent.EXTRA_SUBJECT,"test message");
            intent.putExtra(intent.EXTRA_TEXT,"just sending text from my android app");
            intent.setType("plain/text");
            chooser = intent.createChooser(intent,"mail to");

            startActivity(chooser);
        }
        if(v.getId() == R.id.button3){

//            intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("market://details?id=com.skype.raider"));
//            chooser = intent.createChooser(intent,"launch market");
//            startActivity(chooser);

            LayoutInflater linflater = getLayoutInflater();
            View appear = linflater.inflate(R.layout.customtost,(ViewGroup)findViewById(R.id.tost));


            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.setView(appear);
            toast.show();



        }
    }


}
