package com.stardust.express.app.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylar on 15/5/3.
 */
public class GoodsNameEntity {

    public String name;
    public List<GoodsNameEntity> children;

    public GoodsNameEntity(String name) {
        this.name = name;
        this.children = new ArrayList<GoodsNameEntity>();
    }

    public GoodsNameEntity() {
    }


    @Override
    public String toString() {
        return name;
    }
}
