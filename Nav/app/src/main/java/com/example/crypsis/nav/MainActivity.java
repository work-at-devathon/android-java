package com.example.crypsis.nav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void send(View v)
    {

        EditText e=(EditText)findViewById(R.id.et);
String s=e.getText().toString();
        Intent i=new Intent(this,second.class);
i.putExtra("display", s);

        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            String name = data.getExtras().getString("name");

            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
    }



}
