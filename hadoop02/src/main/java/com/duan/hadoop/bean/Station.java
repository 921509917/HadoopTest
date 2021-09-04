package com.duan.hadoop.bean;

import java.util.Arrays;

public class Station {
    private String name; //stn-wban
    private String[] value;//[经度lon, 维度lat]

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", value=" + Arrays.toString(value) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }
}
