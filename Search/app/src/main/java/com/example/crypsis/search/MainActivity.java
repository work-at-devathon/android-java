package com.example.crypsis.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    SearchView sv;
    String[] fruits={"apple","orange","mango","banana","papaya","pineapple","kiwi","pomegranate","sugarcane","watermelon","guava","lichi"};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);
        sv=(SearchView)findViewById(R.id.searchView);
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.text,fruits);
        lv.setAdapter(arrayAdapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
