package com.example.crypsis.retro;


import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface GoodsAPI {
    @GET("/xyz/goods.json")
    public void getGoods(Callback<List<Customer>> response);
}
