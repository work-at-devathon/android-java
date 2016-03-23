package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MyActivity extends AppCompatActivity {

    //ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
     //  l =(ListView)findViewById(R.id.lv);

        String s = getIntent().getStringExtra("key");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hotels");
        query.whereEqualTo("cityId", "I5VX2r3Zh2");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null) {
                            for (int i = 0; i < objects.size(); i++) {
                                //Toast.makeText(MyActivity.this,i,Toast.LENGTH_SHORT).show();

                                //            ArrayAdapter<ParseObject> arrayAdapter = new ArrayAdapter<ParseObject>(MyActivity.this,R.layout.text,objects);
                                //         l.setAdapter(arrayAdapter);
                                Log.d("hotels", "Retrieved ");
                            }
                        } else {
                            Log.d("Hotels", "Error: " + e.getMessage());
                        }
                    }
                });
    }
}
