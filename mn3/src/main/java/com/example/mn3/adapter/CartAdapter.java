package com.example.mn3.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mn3.R;
import com.example.mn3.bean.CartBean;
import com.example.mn3.callback.CartCallback;
import com.example.mn3.width.AddMinusView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {
    private Context context;
    private List<CartBean.Result> list;
    private CartCallback cartCallback;

    public void setCartCallback(CartCallback cartCallback) {
        this.cartCallback = cartCallback;
    }

    public CartAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<CartBean.Result> list) {
        if(list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartitem, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.VH vh, final int i) {
       vh.name.setText(list.get(i).commodityName);
       vh.price.setText(list.get(i).price+"");
       Uri uri = Uri.parse(list.get(i).pic);
       vh.img.setImageURI(uri);
       vh.checkBox.setChecked(list.get(i).checked);
       vh.checkBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               list.get(i).checked=vh.checkBox.isChecked();
               notifyDataSetChanged();
               for (CartBean.Result result : list) {
                   if(!result.checked){
                       cartCallback.notifyCartItem(false);
                       return;
                   }
                       cartCallback.notifyCartItem(true);
               }
               if(cartCallback!=null){
                   cartCallback.nofityNum();
               }
           }
       });
        vh.addMinusView.setAddminCallback(new AddMinusView.AddminCallback() {
            @Override
            public void addmincallback(int num) {
                list.get(i).num=num;
                if(cartCallback!=null){
                    cartCallback.nofityNum();
                }
            }
        });
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(list.get(i).commodityId);
            }
        });
        vh.addMinusView.setNum(list.get(i).num);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private SimpleDraweeView img;
        private TextView name,price;
        private AddMinusView addMinusView;
        public VH(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            addMinusView=itemView.findViewById(R.id.addminusView);
        }
    }
}
