package com.example.crypsis.cardview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Card View");
        String title = actionBar.getTitle().toString();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

        public void display(MenuItem menuItem){

            Toast.makeText(getApplicationContext(), "Mail", Toast.LENGTH_SHORT).show();
    }
    public void display1(MenuItem mi)
    {
        Toast.makeText(getApplicationContext(), "Contacts", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.m, menu);
        return true;
    }
}
