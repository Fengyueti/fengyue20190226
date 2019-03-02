package com.example.lenovo.fengyue20190226.presenter;

import com.example.lenovo.fengyue20190226.contract.Contract;
import com.example.lenovo.fengyue20190226.model.ShowModel;
import com.example.lenovo.fengyue20190226.net.RetrofitCallback;

import java.util.HashMap;

public class ShowPresenter extends Contract.CPresenter {
    private ShowModel showModel;
    private Contract.CView cView;

    public ShowPresenter( Contract.CView cView) {
        this.showModel = new ShowModel();
        this.cView = cView;
    }

    @Override
    public void gethotmovie(String api, HashMap<String, String> params, HashMap<String, String> params1) {
        if(showModel!=null){
            showModel.gethotmovie(api, params, params1, new RetrofitCallback() {
                @Override
                public void success(Object o) {
                    if(cView!=null){
                        cView.onHotSuccess(o);
                    }
                }

                @Override
                public void failure(String msg) {
                    if(cView!=null){
                        cView.onFailure(msg);
                    }
                }
            });
        }
    }

    @Override
    public void getmoving(String api, HashMap<String, String> params, HashMap<String, String> params1) {
        if(showModel!=null){
            showModel.getmoving(api, params, params1, new RetrofitCallback() {
                @Override
                public void success(Object o) {
                    if(cView!=null){
                        cView.onMovingsuccess(o);
                    }
                }

                @Override
                public void failure(String msg) {
                        cView.onFailure(msg);
                }
            });
        }
    }

    public void destroys(){
        if(cView!=null){
            cView=null;
        }
    }
}
