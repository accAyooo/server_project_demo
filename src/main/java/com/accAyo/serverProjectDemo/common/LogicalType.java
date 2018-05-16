package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:53 2018/5/16
 */
public enum LogicalType {
    And("and"), Or("or");

    private String value;

    private LogicalType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
