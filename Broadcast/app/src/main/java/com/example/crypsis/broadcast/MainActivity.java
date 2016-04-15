package com.example.crypsis.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;
    MyReceiver1 myReceiver1;
    String str1,str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void start(View v) {


        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.MY_ACTION);
        registerReceiver(myReceiver, intentFilter);

        myReceiver1 = new MyReceiver1();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Second.MY_ACTION1);
        registerReceiver(myReceiver1, ifilter);

        EditText editText1=(EditText)findViewById(R.id.et1);
        str1=editText1.getText().toString();
        EditText editText2=(EditText)findViewById(R.id.et2);
        str2=editText2.getText().toString();

        //Start our own service
        Intent intent = new Intent(MainActivity.this,MyService.class);
        intent.putExtra("v1",str1);
        intent.putExtra("v2", str2);
        startService(intent);


    }

   public void stop(View v) {


       stopService(new Intent(getBaseContext(), MyService.class));
       stopService(new Intent(getBaseContext(), Second.class));
//       unregisterReceiver(myReceiver);
//       unregisterReceiver(myReceiver1);

    }
    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {

            Toast.makeText(MainActivity.this,"Triggered by Service!",Toast.LENGTH_SHORT).show();


            Intent i=new Intent(MainActivity.this,Second.class);
            String s=arg1.getStringExtra("value1");
            String s1=arg1.getStringExtra("value2");
            i.putExtra("value1",s);
            i.putExtra("value2",s1);
            startService(i);


        }

    }
    private class MyReceiver1 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this,"end",Toast.LENGTH_SHORT).show();

            String f=intent.getStringExtra("res");
            TextView t=(TextView)findViewById(R.id.tv);
            t.setText(f);

        }
    }
}
