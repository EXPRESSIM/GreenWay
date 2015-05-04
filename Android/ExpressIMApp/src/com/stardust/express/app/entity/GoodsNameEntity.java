package com.stardust.express.app.entity;

import java.util.List;

/**
 * Created by Sylar on 15/5/3.
 */
public class GoodsNameEntity {

    public int id;
    public String name;
    public List<GoodsNameEntity> children;

    public GoodsNameEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GoodsNameEntity(int id, String name, List<GoodsNameEntity> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public GoodsNameEntity() {
    }


    @Override
    public String toString() {
        return name;
    }
}
