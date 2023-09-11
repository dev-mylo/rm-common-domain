package com.rm.domain.type;

import com.rm.domain.common.CodeEnumModel;

public enum UseYn implements CodeEnumModel {

    Y(1,"Y"),

    N(2,"N");

    private int type;
    private String name;

    UseYn(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getCode() {
        return getType();
    }
}