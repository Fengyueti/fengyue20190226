package com.example.mn3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mn3.R;
import com.example.mn3.api.Api;
import com.example.mn3.bean.XqBean;
import com.example.mn3.contract.Contract;
import com.example.mn3.presenter.CartPresenter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XiangActivity extends AppCompatActivity implements Contract.CView {

    private String id;
    private CartPresenter cartPresenter;
    private TextView price,name;
    private XBanner xBanner;
    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        final Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Toast.makeText(this,"xq"+ id,Toast.LENGTH_SHORT).show();
        cartPresenter = new CartPresenter(this);
        HashMap<String,String> params1=new HashMap<>();
        params1.put("userId","202");
        HashMap<String,String> params=new HashMap<>();
        params.put("sessionId","1551489234333202");
        cartPresenter.getxqlist(Api.XIANG_API,params,params1);
        initView();
    }

    private void initView() {
        price = findViewById(R.id.price);
        name =  findViewById(R.id.name);
        xBanner = findViewById(R.id.xbanner);
        webview =findViewById(R.id.webview);
        //设置可以缩放
        webview.getSettings().setSupportZoom(true);
        //设置扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webview.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    public void onCartSuccess(Object o) {

    }

    @Override
    public void onfailure(String msg) {

    }

    @Override
    public void xqSuccess(Object o) {
        final XqBean xqBean= (XqBean) o;
        /*String picture = xqBean.result.picture;
        final String[] split = picture.split("\\,");
        final List<String> list=new ArrayList<>();
        for (int i=0;i<split.length;i++){
            list.add(split[split.length-i-1]);
        }
        //xBanner.setData(list,null);

        name.setText(xqBean.result.categoryName);
        price.setText(xqBean.result.price);
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadData(xqBean.result.details,"text/html","utf-8");*/
    }
}
