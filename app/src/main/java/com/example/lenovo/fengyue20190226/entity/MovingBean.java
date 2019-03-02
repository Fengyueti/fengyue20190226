package com.example.lenovo.fengyue20190226.entity;

import java.util.List;

public class MovingBean {
    public String message;
    public String status;
    public List<ResultBeans> result;
    public class ResultBeans{
        public String id;
        public String imageUrl;
        public String name;
        public String releaseTimeShow;
    }
}
