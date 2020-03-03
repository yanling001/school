package com.example.demo.pojo.vo;

import java.io.Serializable;

public class OrderNumber  implements Serializable {
    private int num;
    private int id;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
