package com.example.crypsis.net;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context=this;
        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Picasso.with(context).load(imageUri).into(image);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.google.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onRetry(int retryNo) {
                // called when request is retried

            }
});
    }
}
