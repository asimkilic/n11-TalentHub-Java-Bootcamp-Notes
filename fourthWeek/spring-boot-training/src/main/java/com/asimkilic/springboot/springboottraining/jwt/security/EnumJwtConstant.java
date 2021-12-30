package com.asimkilic.springboot.springboottraining.jwt.security;

public enum EnumJwtConstant {
    BEARER("Berarer ");
    private String constant;

    EnumJwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
