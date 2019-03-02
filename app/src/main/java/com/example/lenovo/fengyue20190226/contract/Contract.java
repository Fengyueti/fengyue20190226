package com.example.lenovo.fengyue20190226.contract;

import com.example.lenovo.fengyue20190226.net.RetrofitCallback;

import java.util.HashMap;

public interface Contract {
    public abstract class CPresenter{
        public abstract void gethotmovie(String api, HashMap<String, String> params, HashMap<String, String> params1);
        public abstract void getmoving(String api, HashMap<String, String> params, HashMap<String, String> params1);
    }
    interface CModel{
        public void gethotmovie(String api, HashMap<String,String> params, HashMap<String,String> params1, RetrofitCallback retrofitCallback);
        public void getmoving(String api, HashMap<String,String> params, HashMap<String,String> params1, RetrofitCallback retrofitCallback);
    }
    interface CView{
        void onHotSuccess(Object o);
        void onFailure(String msg);
        void onMovingsuccess(Object o);
    }
}
