package com.stardust.express.app.entity;

import java.io.Serializable;

/**
 * Created by Sylar on 15/5/19.
 */
public class KeyValuePair implements Serializable {

    public int key;
    public String value;


    public KeyValuePair(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValuePair() {
    }

    @Override
    public String toString() {
        return value;
    }
}
