/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    SearchView sv;
    List<String> arrayList;MyAdapter myAdapter;
    ExpandableListAdapter expandableListAdapter;
    ArrayAdapter<String> arrayAdapter;
    HashMap<String,ArrayList<ParseObject>> result = new HashMap<String, ArrayList<ParseObject>>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
     expandableListView=(ExpandableListView)findViewById(R.id.listView);
      sv=(SearchView)findViewById(R.id.searchView);

   // ParseAnalytics.trackAppOpenedInBackground(getIntent());

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, com.parse.ParseException e) {

              if (e == null) {
                  for (ParseObject obj: objects) {
                      String s = (String)obj.get("country");
                      ArrayList<ParseObject> temp = result.get(s);
                      if(temp == null){
                          ArrayList<ParseObject> a = new ArrayList<ParseObject>();
                          a.add(obj);
                          result.put(s, a);
                          Log.d("map", "loaded");
                      }
                      else {
                          temp.add(obj);
                      }
                  }

                  myAdapter = new MyAdapter(MainActivity.this,result);
                  expandableListView.setAdapter(myAdapter);
                 // expandableListView.setTextFilterEnabled(true);

                  sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                      @Override
                      public boolean onQueryTextSubmit(String query) {
                          return false;
                      }

                      @Override
                      public boolean onQueryTextChange(String newText) {
                       //  myAdapter.getFilter().filter(newText);
                          return false;
                      }
                  });

                  Log.d("cities", "Retrieved ");
              }
              else {
                  Log.d("Cities", "Error: " + e.getMessage());
              }
          }


      });

      expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              String s=((TextView)view).getText().toString();
              Intent intent = new Intent(MainActivity.this,MyActivity.class);
              intent.putExtra("key",s);
              startActivity(intent);
          }
      });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
