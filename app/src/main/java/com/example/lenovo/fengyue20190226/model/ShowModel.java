package com.example.lenovo.fengyue20190226.model;

import com.example.lenovo.fengyue20190226.contract.Contract;
import com.example.lenovo.fengyue20190226.net.RetrofitCallback;
import com.example.lenovo.fengyue20190226.net.RetrofitUtils;

import java.util.HashMap;

public class ShowModel implements Contract.CModel {
    @Override
    public void gethotmovie(String api, HashMap<String, String> params, HashMap<String, String> params1, RetrofitCallback retrofitCallback) {
        RetrofitUtils.getmInstance().hotmovie(api,params,params1,retrofitCallback);
    }

    @Override
    public void getmoving(String api, HashMap<String, String> params, HashMap<String, String> params1, RetrofitCallback retrofitCallback) {
        RetrofitUtils.getmInstance().moving(api,params,params1,retrofitCallback);
    }
}
