package com.example.lenovo.fengyue20190226.entity;

import java.util.List;

public class HotMoviceBean {
    public String message;
    public String status;
    public List<ResultBean> result;
    public class ResultBean{
        public String imageUrl;
        public String name;
        public String rank;
        public String id;
        public String followMovie;
    }
}
