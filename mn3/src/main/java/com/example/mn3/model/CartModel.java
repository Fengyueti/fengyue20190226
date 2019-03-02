package com.example.mn3.model;

import com.example.mn3.contract.Contract;
import com.example.mn3.net.RetrofitCallback;
import com.example.mn3.net.RetrofitUtils;

import java.util.HashMap;

public class CartModel implements Contract.CModel {

    @Override
    public void getcartlist(String api, HashMap<String, String> params, RetrofitCallback retrofitCallback) {
        RetrofitUtils.getmInstance().setcartlist(api,params,retrofitCallback);
    }

    @Override
    public void getxqlist(String api, HashMap<String, String> params, HashMap<String, String> params1, RetrofitCallback retrofitCallback) {
        RetrofitUtils.getmInstance().setxq(api,params,params1,retrofitCallback);
    }
}
