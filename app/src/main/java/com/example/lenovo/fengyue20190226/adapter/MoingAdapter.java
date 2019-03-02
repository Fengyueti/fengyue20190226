package com.example.lenovo.fengyue20190226.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.fengyue20190226.R;
import com.example.lenovo.fengyue20190226.entity.HotMoviceBean;
import com.example.lenovo.fengyue20190226.entity.MovingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MoingAdapter extends RecyclerView.Adapter<MoingAdapter.VH> {
    private Context context;
    private List<MovingBean.ResultBeans> list;

    public MoingAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<MovingBean.ResultBeans> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setadd1(List<MovingBean.ResultBeans> list) {
       list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoingAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotitem, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoingAdapter.VH vh, int i) {
        vh.name.setText(list.get(i).name);
        vh.price.setText(list.get(i).releaseTimeShow);
        Uri uri = Uri.parse(list.get(i).imageUrl);
        vh.img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView name,price;
        public VH(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
}
