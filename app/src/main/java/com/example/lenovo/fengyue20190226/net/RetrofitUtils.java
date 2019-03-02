package com.example.lenovo.fengyue20190226.net;

import com.example.lenovo.fengyue20190226.api.HotApiService;
import com.example.lenovo.fengyue20190226.api.MovingApiService;
import com.example.lenovo.fengyue20190226.api.UserApi;
import com.example.lenovo.fengyue20190226.entity.HotMoviceBean;
import com.example.lenovo.fengyue20190226.entity.MovingBean;

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
                .addNetworkInterceptor(loggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(UserApi.BASE_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitUtils getmInstance() {
        if(mInstance==null){
            synchronized (RetrofitUtils.class){
                mInstance=new RetrofitUtils();
            }
        }
        return mInstance;
    }
    public void hotmovie(String api, HashMap<String,String> params, HashMap<String,String> params1, final RetrofitCallback retrofitCallback){
        HotApiService hotApiService = retrofit.create(HotApiService.class);
        Call<HotMoviceBean> hotmovie = hotApiService.hotmovie(api, params, params1);
        hotmovie.enqueue(new Callback<HotMoviceBean>() {
            @Override
            public void onResponse(Call<HotMoviceBean> call, Response<HotMoviceBean> response) {
                HotMoviceBean body = response.body();
                retrofitCallback.success(body);
            }

            @Override
            public void onFailure(Call<HotMoviceBean> call, Throwable t) {
                retrofitCallback.failure(t.toString());
            }
        });
    }
    public void moving(String api, HashMap<String,String> params, HashMap<String,String> params1, final RetrofitCallback retrofitCallback){
        MovingApiService movingApiService = retrofit.create(MovingApiService.class);
        Call<MovingBean> moving = movingApiService.moving(api, params, params1);
        moving.enqueue(new Callback<MovingBean>() {
            @Override
            public void onResponse(Call<MovingBean> call, Response<MovingBean> response) {
                MovingBean body = response.body();
                retrofitCallback.success(body);
            }

            @Override
            public void onFailure(Call<MovingBean> call, Throwable t) {
                retrofitCallback.failure(t.toString());
            }
        });
    }
}
