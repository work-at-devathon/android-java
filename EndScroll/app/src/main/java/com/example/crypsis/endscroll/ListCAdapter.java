package com.example.crypsis.endscroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListCAdapter extends BaseAdapter {
    List<String> l;
    Context context;
    public ListCAdapter(Context context){
        this.l = new ArrayList<String>();
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.l.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // either we inflate a layout.
        // or create ui elements progrmatically
        // also need to consider caching

        View v;
        if (convertView == null) {
           LayoutInflater li =(LayoutInflater)context.getSystemService
                   (Context.LAYOUT_INFLATER_SERVICE);

            v = li.inflate(R.layout.scroll, null);
           // ListView tv = (ListView) v.findViewById(R.id.listview);

            String item = this.l.get(position);
            TextView tv = (TextView)v;
            tv.setText(item);
        } else {
            v = convertView;
        }
        return v;
    }




    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    public void add(String s){
        this.l.add(s);
    }
}
