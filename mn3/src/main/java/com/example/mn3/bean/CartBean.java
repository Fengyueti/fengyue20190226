package com.example.mn3.bean;

import java.util.List;

public class CartBean {
    public String message;
    public String status;
    public List<Result> result;
    public class Result{
            public boolean checked;
            public int num;
            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public double price;
    }
}
