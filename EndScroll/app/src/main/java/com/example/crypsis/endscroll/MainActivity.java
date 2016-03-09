package com.example.crypsis.endscroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

    ListCAdapter listCAdapter = new ListCAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i =0; i<20;i++) {
            listCAdapter.add("Title " + i);
        }
        ListView l = (ListView) findViewById(R.id.listview);
        l.setAdapter(listCAdapter);
        listCAdapter.notifyDataSetChanged();
        //TextView tv = (TextView) findViewById(R.id.tv);
       // tv.setMovementMethod(new ScrollingMovementMethod());

        l.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // 1 -20 , 21 --- 1--20 , 1..20

               int e=(page*totalItemsCount)-1;
                for(int s=(page-1)*totalItemsCount; s<=e;s++){
                    listCAdapter.add("New Title "+s);
                }
                MainActivity.this.listCAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }
}
