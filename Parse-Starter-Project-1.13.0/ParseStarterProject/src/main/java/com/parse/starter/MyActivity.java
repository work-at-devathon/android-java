package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    MyCardContainer cardContainer;
    List<HotelInfo> hinfo = new ArrayList<>();
    List<MyCardModel> hinfo1 = new ArrayList<>();
    HotelAdapter hotelAdapter;
    MyCardAdapter myCardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
          //cardContainer = (MyCardContainer) findViewById(R.id.layoutview1);


        String s = getIntent().getStringExtra("key");
       // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hotels");
        query.whereEqualTo("cityId", s);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : objects) {
                        MyCardModel m = new MyCardModel();
                        m.hotelname = (String) parseObject.get("name");
                        m.address = (String) parseObject.get("address");
                        m.rating = (String) parseObject.get("rating").toString();
                        m.imageUrl = (String) parseObject.get("image");
                        hinfo1.add(m);
                        HotelInfo hi = new HotelInfo();
                        hi.hotelname = (String) parseObject.get("name");
                        hi.address = (String) parseObject.get("address");
                        hi.rating = (String) parseObject.get("rating").toString();
                        hi.imageUri = (String) parseObject.get("image");
                        hinfo.add(hi);
                        Log.d("hotels", "Retrieved ");
                    }
                   hotelAdapter.notifyDataSetChanged();
                    myCardAdapter.notifyDataSetChanged();
                } else {
                    Log.d("Hotels", "Error: " + e.getMessage());
                }
            }
        });
        myCardAdapter=new MyCardAdapter(this,hinfo1);
       hotelAdapter = new HotelAdapter(hinfo);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyCardModel myCardModel=new MyCardModel();
        myCardModel.setOnClickListener(new MyCardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Toast.makeText(getApplicationContext(), "onclicklistener", Toast.LENGTH_SHORT).show();
            }
        });
        myCardModel.setOnCardDimissedListener(new MyCardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Toast.makeText(getApplicationContext(), "onlike", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDislike() {
                Toast.makeText(getApplicationContext(), "ondislike", Toast.LENGTH_SHORT).show();
            }
        });

       // myCardAdapter.add(myCardModel);

       //cardContainer.setAdapter(myCardAdapter);
    }
}
