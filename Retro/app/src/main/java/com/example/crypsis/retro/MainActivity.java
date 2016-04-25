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
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {


    public static final String ROOT_URL = "https://www.discovered.us/";
    public static final String KEY_SELLER_ID = "key_seller_id";
    public static final String KEY_SELLER_NAME = "key_seller_name";
    public static final String KEY_DISPLAY_PRICE = "key_display_price";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_SELLER_URL = "key_seller_url";
    private ListView listView;
    public Call<Example> customers;
    public List<Customer> res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewBooks);
        getBooks();
        listView.setOnItemClickListener(this);
    }

    private void getBooks() {

        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GoodsAPI goodsAPI = retrofit.create(GoodsAPI.class);
        goodsAPI.getGoods().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                loading.dismiss();
                res=response.body().getProduct();
                showList();

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void showList() {

        String[] items = new String[res.size()];

        for (int i = 0; i < res.size(); i++) {
            items[i] = res.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, Sgd.class);
        Customer customer = res.get(position);
        intent.putExtra(KEY_SELLER_ID, customer.getSellerId());
        intent.putExtra(KEY_SELLER_NAME, customer.getSellerName());
        intent.putExtra(KEY_NAME, customer.getName());
        intent.putExtra(KEY_SELLER_URL, customer.getSellerUrl());
        intent.putExtra(KEY_DISPLAY_PRICE, customer.getPrice());
        startActivity(intent);
    }
}