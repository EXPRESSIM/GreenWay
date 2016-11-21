package com.stardust.express.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylar on 15/5/3.
 */
public class GoodsNameEntity implements Serializable {

    public String name;
    public String pinyin;
    public List<GoodsNameEntity> children;
    public boolean isChecked = false;

    public GoodsNameEntity(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
        this.children = new ArrayList<GoodsNameEntity>();
    }

    public GoodsNameEntity() {
    }


    @Override
    public String toString() {
        return name;
    }
}
