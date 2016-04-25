package com.example.crypsis.retro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Sgd extends AppCompatActivity {
    private TextView textViewSellerId;
    private TextView textViewName;
    private TextView textViewDisplayPrice;
    private TextView textViewSellerUrl;
    private TextView textViewSellerName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgd);
        textViewSellerId=(TextView)findViewById(R.id.textViewSellerId);
        textViewSellerName=(TextView)findViewById(R.id.textViewSellerName);
        textViewName=(TextView)findViewById(R.id.textViewName);
        textViewSellerUrl=(TextView)findViewById(R.id.textViewSellerUrl);
        textViewDisplayPrice=(TextView)findViewById(R.id.textViewDisplayPrice);


        Intent intent = getIntent();

        textViewSellerId.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_SELLER_ID, 0)));
        textViewSellerName.setText(intent.getStringExtra(MainActivity.KEY_SELLER_NAME));
        textViewName.setText(intent.getStringExtra(MainActivity.KEY_NAME));
        textViewSellerUrl.setText(intent.getStringExtra(MainActivity.KEY_SELLER_URL));
        textViewDisplayPrice.setText(intent.getStringExtra(MainActivity.KEY_DISPLAY_PRICE));;


    }

}
