package com.example.mn3.contract;

import com.example.mn3.net.RetrofitCallback;

import java.util.HashMap;

public interface Contract {
    public abstract class CPresenter{
        public abstract void getcartlist(String api, HashMap<String, String> params);
        public abstract void getxqlist(String api, HashMap<String, String> params, HashMap<String, String> params1);

    }
    interface CModel{
        public void getcartlist(String api, HashMap<String,String> params, RetrofitCallback retrofitCallback);
        public void getxqlist(String api, HashMap<String,String> params,HashMap<String,String> params1, RetrofitCallback retrofitCallback);
    }
    interface CView{
        void onCartSuccess(Object o);
        void onfailure(String msg);
        void xqSuccess(Object o);
    }
}
