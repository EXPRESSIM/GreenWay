package com.stardust.express.app.entity;

import java.io.Serializable;

/**
 * Created by Gyb on 2015/5/8.
 */
public class StationEntity implements Serializable {
    public String name;
    public String pinyin;
    public boolean checked;


    public StationEntity() {
    }

    public StationEntity(String name) {
    }

    public StationEntity(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
        this.checked = false;
    }
}
