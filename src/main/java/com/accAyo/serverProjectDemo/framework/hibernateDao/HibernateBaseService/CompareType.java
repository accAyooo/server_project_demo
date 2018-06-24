package com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:39 2018/5/16
 */
public enum  CompareType {
    Equal("="), NotEqual("<>"), Gt(">"), Lt("<"), Ge(">="), Le("<="), Like(
            " like "), NotLike(" not like ");

    private String value;

    private CompareType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
