package com.example.mn3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mn3.R;
import com.example.mn3.activity.OrderActivity;
import com.example.mn3.activity.XiangActivity;
import com.example.mn3.adapter.CartAdapter;
import com.example.mn3.api.Api;
import com.example.mn3.bean.CartBean;
import com.example.mn3.callback.CartCallback;
import com.example.mn3.contract.Contract;
import com.example.mn3.presenter.CartPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

public class CartFragment extends Fragment implements Contract.CView,CartCallback {

    private XRecyclerView cartrv;
    private CheckBox checkbox;
    private TextView sum;
    private TextView js;
    private CartPresenter presenter;
    private HashMap<String, String> params;
    private CartAdapter adapter;
    private List<CartBean.Result> result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cartfragment, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        initData(view);
        return view;
    }

    private void initData(View view) {
        cartrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new CartPresenter(this);
        params =new HashMap<>();
        params.put("userId","202");
        params.put("sessionId","1551489234333202");
        presenter.getcartlist(Api.CART_API,params);
        adapter = new CartAdapter(getActivity());
        adapter.setCartCallback(this);
        cartrv.setAdapter(adapter);
        //全选的点击事件
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for (CartBean.Result result1 : result) {
                        result1.checked=true;
                    }
                }else{
                    for (CartBean.Result result1 : result) {
                        result1.checked=false;
                    }
                }
                adapter.notifyDataSetChanged();
                getTotalPrice();
            }
        });
        //结算的点击事件
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),OrderActivity.class));
            }
        });


    }
    @Subscribe
    public void accepts(String s){
        Intent intent=new Intent(getActivity(),XiangActivity.class);
        intent.putExtra("id",s);
        startActivity(intent);
    }
    private void initView(View view) {
        cartrv =view.findViewById(R.id.cartrv);
        checkbox =view.findViewById(R.id.checkbox);
        sum =view.findViewById(R.id.sum);
        js =view.findViewById(R.id.js);
    }


    @Override
    public void onCartSuccess(Object o) {
        CartBean cartBean= (CartBean) o;
        result = cartBean.result;
        Toast.makeText(getActivity(),"成功",Toast.LENGTH_SHORT).show();
        adapter.setList(result);
        if(result!=null){
            for (CartBean.Result result1 : result) {
                result1.num=1;
            }
        }
    }

    @Override
    public void onfailure(String msg) {

    }

    @Override
    public void xqSuccess(Object o) {

    }

    public void getTotalPrice(){
        double gettotalprice=0;
        for (CartBean.Result result1 : result) {
            if(result1.checked) {
                gettotalprice = result1.price * result1.num;
            }
        }
        sum.setText(gettotalprice+"");
    }


    @Override
    public void notifyCartItem(boolean checked) {
       checkbox.setChecked(checked);
    }

    @Override
    public void nofityNum() {
        getTotalPrice();
    }
}
