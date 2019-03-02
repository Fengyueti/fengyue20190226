package com.example.lenovo.fengyue20190226.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.fengyue20190226.R;
import com.example.lenovo.fengyue20190226.adapter.HotAdapter;
import com.example.lenovo.fengyue20190226.adapter.MoingAdapter;
import com.example.lenovo.fengyue20190226.api.UserApi;
import com.example.lenovo.fengyue20190226.contract.Contract;
import com.example.lenovo.fengyue20190226.entity.HotMoviceBean;
import com.example.lenovo.fengyue20190226.entity.MovingBean;
import com.example.lenovo.fengyue20190226.presenter.ShowPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.CView,XRecyclerView.LoadingListener{

    private XRecyclerView rv,rv1;
    private ShowPresenter presenter;
    private HotAdapter adapter;
    private int page=1;
    private int count=10;
    private int count1=20;
    private String userId="11249";
    private String sessionId="155056366467311249";
    private HashMap<String, String> params;
    private HashMap<String, String> params1;
    private TextView back,dd,ryy,sy;
    private MoingAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         back =findViewById(R.id.back);
         dd =findViewById(R.id.dd);
         rv = findViewById(R.id.rv);
         rv1 = findViewById(R.id.rv1);
         ryy =findViewById(R.id.ry);
         sy = findViewById(R.id.sy);
         rv.setLayoutManager(new GridLayoutManager(this,2));
         rv1.setLayoutManager(new GridLayoutManager(this,2));
         rv.setLoadingMoreEnabled(true);
         rv1.setLoadingMoreEnabled(true);
         presenter = new ShowPresenter(this);
         params =new HashMap<>();
         params.put("page",page+"");
         params.put("count",count+"");
         params1 =new HashMap<>();
         params1.put("userId",userId);
         params1.put("sessionId",sessionId);
         presenter.gethotmovie(UserApi.HOT_API,params,params1);
         presenter.getmoving(UserApi.MOVING_API,params,params1);

         adapter = new HotAdapter(this);
         adapter1 = new MoingAdapter(this);
         rv.setAdapter(adapter);
         rv1.setAdapter(adapter1);

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
         dd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ryy.setVisibility(View.VISIBLE);
                 sy.setVisibility(View.VISIBLE);
             }
         });
         sy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 rv.setVisibility(View.GONE);
                 rv1.setVisibility(View.INVISIBLE);
             }
         });
    }

    @Override
    public void onHotSuccess(Object o) {
        HotMoviceBean hotMoviceBean= (HotMoviceBean) o;
        List<HotMoviceBean.ResultBean> result = hotMoviceBean.result;
        if(page==1){
            adapter.setList(result);
        }else {
            adapter.setadd(result);
        }
        page++;

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onMovingsuccess(Object o) {
        MovingBean movingBean= (MovingBean) o;
        List<MovingBean.ResultBeans> result = movingBean.result;
        if(page==1){
            adapter1.setList(result);
        }else{
            adapter1.setadd1(result);
        }
        page++;
    }

    @Override
    public void onRefresh() {
        params.put("page",page+"");
        rv1.refreshComplete();
        rv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        params.put("page",page+"");
        rv1.loadMoreComplete();
        rv.loadMoreComplete();
    }
}
