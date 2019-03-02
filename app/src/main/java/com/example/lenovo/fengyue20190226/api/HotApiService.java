package com.example.lenovo.fengyue20190226.api;

import com.example.lenovo.fengyue20190226.entity.HotMoviceBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface HotApiService {
    @GET
    Call<HotMoviceBean> hotmovie(@Url String url, @QueryMap HashMap<String,String> params, @HeaderMap HashMap<String,String>  params1);
}
