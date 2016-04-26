package com.example.crypsis.retro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final String ROOT_URL = "https://www.discovered.us/";
    public static final String KEY_SELLER_IMAGE = "key_seller_id";
    public static final String KEY_SELLER_NAME = "key_seller_name";
    public static final String KEY_DISPLAY_PRICE = "key_display_price";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_IMAGE_URL = "key_seller_url";
    private ListView listView;
    public Call<Example> customers;
    public List<Customer> res;
    List<Customer> cinfo = new ArrayList<>();
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        getBooks();
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
                res = response.body().getProduct();
                showList();

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void showList() {

        for (int i = 0; i < res.size(); i++) {
            Customer c = new Customer();
            c.name = res.get(i).getName();
            c.seller_name = res.get(i).getSellerName();
            c.image_url = res.get(i).getImageUrl();
            c.display_price = res.get(i).getPrice();
            c.seller_image = res.get(i).getSellerImage();
            cinfo.add(c);
        }
//        myRecyclerViewAdapter.notifyDataSetChanged();
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(cinfo);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}