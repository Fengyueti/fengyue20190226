package com.example.mn3.api;

import com.example.mn3.bean.CartBean;

import org.xml.sax.helpers.XMLReaderAdapter;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

public interface CartApiService {
    @GET
    Call<CartBean> cartbean(@Url String url, @HeaderMap HashMap<String,String> params);
}
