package com.example.mn3.presenter;

import com.example.mn3.contract.Contract;
import com.example.mn3.model.CartModel;
import com.example.mn3.net.RetrofitCallback;

import java.util.HashMap;

public class CartPresenter extends Contract.CPresenter {
    private CartModel cartModel;
    private Contract.CView cView;

    public CartPresenter(Contract.CView cView) {
        this.cartModel = new CartModel();
        this.cView = cView;
    }

    @Override
    public void getcartlist(String api, HashMap<String, String> params) {
        if(cartModel!=null){
            cartModel.getcartlist(api, params, new RetrofitCallback() {
                @Override
                public void onsuccess(Object o) {
                    if(cView!=null){
                        cView.onCartSuccess(o);
                    }
                }

                @Override
                public void onfailure(String msg) {
                    if(cView!=null){
                        cView.onfailure(msg);
                    }
                }
            });
        }
    }

    @Override
    public void getxqlist(String api, HashMap<String, String> params, HashMap<String, String> params1) {
        if(cartModel!=null){
            cartModel.getxqlist(api, params, params1, new RetrofitCallback() {
                @Override
                public void onsuccess(Object o) {
                    if(cView!=null){
                        cView.xqSuccess(o);
                    }
                }

                @Override
                public void onfailure(String msg) {
                    if(cView!=null){
                        cView.onfailure(msg);
                    }
                }
            });
        }
    }

    public void destroys(){
        if (cView!=null){
            cView=null;
        }
    }
}
