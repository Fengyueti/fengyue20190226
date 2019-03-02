package com.example.mn3.net;

import com.example.mn3.api.Api;
import com.example.mn3.api.CartApiService;
import com.example.mn3.api.XqApiService;
import com.example.mn3.bean.CartBean;
import com.example.mn3.bean.XqBean;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private static RetrofitUtils mInstance;
    public RetrofitUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitUtils getmInstance() {
        if(mInstance==null){
            mInstance=new RetrofitUtils();
        }
        return mInstance;
    }
    public void setcartlist(String api, HashMap<String,String> params, final RetrofitCallback retrofitCallback){
        CartApiService cartApiService = retrofit.create(CartApiService.class);
        Call<CartBean> cartbean = cartApiService.cartbean(api, params);
        cartbean.enqueue(new Callback<CartBean>() {
            @Override
            public void onResponse(Call<CartBean> call, Response<CartBean> response) {
                CartBean body = response.body();
                retrofitCallback.onsuccess(body);
            }

            @Override
            public void onFailure(Call<CartBean> call, Throwable t) {
                retrofitCallback.onfailure(t.toString());
            }
        });
    }
    public void setxq(String api, HashMap<String,String> params, HashMap<String,String> params1, final RetrofitCallback retrofitCallback){
        XqApiService xqApiService = retrofit.create(XqApiService.class);
        Call<XqBean> xqlist = xqApiService.xqlist(api, params, params1);
        xqlist.enqueue(new Callback<XqBean>() {
            @Override
            public void onResponse(Call<XqBean> call, Response<XqBean> response) {
                XqBean body = response.body();
                retrofitCallback.onsuccess(body);
            }

            @Override
            public void onFailure(Call<XqBean> call, Throwable t) {
                retrofitCallback.onfailure(t.toString());
            }
        });
    }
}
