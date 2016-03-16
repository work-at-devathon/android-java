package com.example.crypsis.parcelable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        Bundle b = getIntent().getExtras();
        ObjectA obj =b.getParcelable("ObjectA");
        TextView textView=(TextView)findViewById(R.id.textView);
        String s=obj.getStrValue();
        textView.setText(s);
      // int a=obj.getIntValue();
       // TextView textView1=(TextView)findViewById(R.id.textView1);
       // textView1.setText(a);


    }
}
