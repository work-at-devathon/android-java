package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    List<HotelInfo> hinfo = new ArrayList<>();
    HotelAdapter hotelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);



        String s = getIntent().getStringExtra("key");
       // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hotels");
        query.whereEqualTo("cityId", s);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : objects) {
                        HotelInfo hi = new HotelInfo();
                        hi.hotelname = (String) parseObject.get("name");
                        hi.address = (String) parseObject.get("address");
                         hi.rating =(String)parseObject.get("rating").toString();
                        hi.imageUri = (String) parseObject.get("image");
                        hinfo.add(hi);
                        Log.d("hotels", "Retrieved ");
                    }
                    hotelAdapter.notifyDataSetChanged();
                } else {
                    Log.d("Hotels", "Error: " + e.getMessage());
                }
            }
        });
        hotelAdapter = new HotelAdapter(hinfo);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
