package com.example.crypsis.nav1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendmsg(View view)
    {
        User u=new User("John","Wick",01234);

        Intent intent=new Intent(this,Second.class);

        intent.putExtra("details",u);

        startActivity(intent);

    }
}
