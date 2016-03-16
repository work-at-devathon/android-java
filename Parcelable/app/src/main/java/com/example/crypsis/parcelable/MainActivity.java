package com.example.crypsis.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectA obj = new ObjectA();
                obj.setIntValue(10);
                obj.setStrValue("avi");
                Intent i = new Intent(MainActivity.this, ReceiverActivity.class);
                i.putExtra("ObjectA", obj);

                startActivity(i);
            }
        });


    }
}
