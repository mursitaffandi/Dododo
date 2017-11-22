package com.mursitaffandi.dododo.network;

import com.mursitaffandi.dododo.model.MLogin;
import com.mursitaffandi.dododo.model.MRegister;
import com.mursitaffandi.dododo.util.ConfigUrl;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by mursitaffandi on 23/11/17.
 */

public interface SLogin {
    Retrofit client = new Retrofit.Builder()
            .baseUrl(ConfigUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Headers({
            "Content-Type:application/x-www-form-urlencoded"
    })
    @POST("login")
    Call<MLogin> getJsonMLogin(@QueryMap Map<String, String> options);
}
