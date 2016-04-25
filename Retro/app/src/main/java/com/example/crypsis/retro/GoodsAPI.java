package com.example.crypsis.retro;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GoodsAPI {
    @GET("goods.json")
    Call<Example> getGoods();
}
