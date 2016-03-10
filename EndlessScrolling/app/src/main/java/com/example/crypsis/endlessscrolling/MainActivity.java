package com.example.crypsis.endlessscrolling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 ArrayList<Contact> contacts;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         // ...
         // Lookup the recyclerview in activity layout
         setContentView(R.layout.activity_users);
         RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

         // Initialize contacts
         contacts = Contact.createContactsList(20);
         // Create adapter passing in the sample user data
         ContactsAdapter adapter = new ContactsAdapter(contacts);
         // Attach the adapter to the recyclerview to populate items

         rvContacts.setAdapter(adapter);
            // int curSize = adapter.getItemCount();
             //adapter.notifyItemRangeInserted(curSize,contacts.size());



         // Set layout manager to position the items
         rvContacts.setLayoutManager(new LinearLayoutManager(this));
         // That's all!
     }
}