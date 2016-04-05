package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    MyCardContainer cardContainer;RelativeLayout r;
    MyCardModel m;
    List<HotelInfo> hinfo = new ArrayList<>();
    List<MyCardModel> hinfo1 = new ArrayList<>();
    HotelAdapter hotelAdapter;
    MyCardAdapter myCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        cardContainer = (MyCardContainer) findViewById(R.id.layoutview1);
  r=(RelativeLayout)findViewById(R.id.global_container);

        String s = getIntent().getStringExtra("key");
        // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
myCardAdapter=new MyCardAdapter(this);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hotels");
        query.whereEqualTo("cityId", s);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject parseObject : objects) {
                        m = new MyCardModel();
                        m.hotelname = (String) parseObject.get("name");
                        m.address = (String) parseObject.get("address");
                        m.rating = (String) parseObject.get("rating").toString();
                        m.imageUrl = (String) parseObject.get("image");
                       // hinfo1.add(m);
                        myCardAdapter.add(m);
//                        HotelInfo hi = new HotelInfo();
//                        hi.hotelname = (String) parseObject.get("name");
//                        hi.address = (String) parseObject.get("address");
//                        hi.rating = (String) parseObject.get("rating").toString();
//                        hi.imageUri = (String) parseObject.get("image");
//                        hinfo.add(hi);

                        m.setOnClickListener(new MyCardModel.OnClickListener() {
                            @Override
                            public void OnClickListener() {
                                //Toast.makeText(getApplicationContext(), "onclicklistener", Toast.LENGTH_SHORT).show();
                            }
                        });
                        m.setOnCardDimissedListener(new MyCardModel.OnCardDimissedListener() {
                            @Override
                            public void onLike() {
                                Toast.makeText(getApplicationContext(), "Like", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onDislike() {
                                Toast.makeText(getApplicationContext(), "Nope", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Log.d("hotels", "Retrieved ");
                    }
                    //hotelAdapter.notifyDataSetChanged();
                     myCardAdapter.notifyDataSetChanged();cardContainer.setAdapter(myCardAdapter);
                } else {
                    Log.d("Hotels", "Error: " + e.getMessage());
                }
            }
        });
        // myCardAdapter=new MyCardAdapter(this,hinfo1);
//        hotelAdapter = new HotelAdapter(hinfo);
//        recyclerView.setAdapter(hotelAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        MyCardModel myCardModel = new MyCardModel();
//        // myCardAdapter.add(m);
//
//        myCardModel.hotelname="abc hotel";
//        myCardModel.address="123";myCardModel.rating="4";myCardModel.imageUrl="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI1WN35C0vWADHlxJ15VHOvmVHnsykdKDTQW4qoJB5y6GyJDBe";
//
//
//        myCardAdapter.add(myCardModel);myCardAdapter.notifyDataSetChanged();

    }
}
