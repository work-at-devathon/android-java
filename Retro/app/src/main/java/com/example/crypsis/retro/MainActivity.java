package com.example.crypsis.retro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//Class having OnItemClickListener to handle the clicks on list
public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    //Root URL of our web service
    public static final String ROOT_URL = "https://www.discovered.us/";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_SELLER_ID = "key_seller_id";
    public static final String KEY_SELLER_NAME = "key_seller_name";
    public static final String KEY_DISPLAY_PRICE = "key_display_price";
    public static final String KEY_NAME= "key_name";
    public static final String KEY_SELLER_URL= "key_seller_url";

    //List view to show data
    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private List<Customer> customers;
//    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBooks);

        //Calling the method that will fetch data
        getBooks();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);
    }

    private void getBooks() {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .build();
        GoodsAPI goodsAPI = retrofit.create(GoodsAPI.class);
        //Creating a rest adapter
//        RestAdapter adapter = new RestAdapter.Builder()
//                .setEndpoint(ROOT_URL)
//                .build();
//
//        //Creating an object of our api interface
//        BooksAPI api = adapter.create(BooksAPI.class);

        //Defining the method
        goodsAPI.getGoods(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                loading.dismiss();

                //Storing the data in our list
//                books = list;


                //Calling a method to show the list
                showList();
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

            }
        });
//        api.getBooks(new Callback<List<Book>>() {
//            @Override
//            public void success(List<Book> list, Response response) {
//                //Dismissing the loading progressbar
//                loading.dismiss();
//
//                //Storing the data in our list
//                books = list;
//
//                //Calling a method to show the list
//                showList();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                //you can handle the errors here
//            }
//        });
    }

    //Our method to show list
    private void showList() {
        String[] items = new String[customers.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < customers.size(); i++) {
            //Storing names to string array
            items[i] = customers.get(i).getName();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
        //String array to store all the book names
//        String[] items = new String[books.size()];
//
//        //Traversing through the whole list to get all the names
//        for (int i = 0; i < books.size(); i++) {
//            //Storing names to string array
//            items[i] = books.get(i).getName();
//        }
//
//        //Creating an array adapter for list view
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);
//
//        //Setting adapter to listview
//        listView.setAdapter(adapter);
    }


    //This method will execute on listitem click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowGoodsDetails.class);

        //Getting the requested book from the list
//        Book book = books.get(position);
        Customer customer=customers.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_SELLER_ID,customer.getSellerId());
        intent.putExtra(KEY_SELLER_NAME, customer.getSellerName());
        intent.putExtra(KEY_NAME, customer.getName());
        intent.putExtra(KEY_SELLER_URL,customer.getSellerUrl());
        intent.putExtra(KEY_DISPLAY_PRICE,customer.getPrice());

        //Starting another activity to show book details
        startActivity(intent);
    }
}