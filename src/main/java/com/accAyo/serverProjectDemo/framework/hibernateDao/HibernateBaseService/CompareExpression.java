package com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SimpleExpression;

import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:40 2018/5/16
 */
public class CompareExpression extends SimpleExpression implements
        Serializable, HibernateExpression {

    String propertyName;
    Object value;
    CompareType compareType;

    public CompareExpression(String propertyName, Object value,
                             CompareType compareType) {
        super(propertyName, value, compareType.getValue());
        this.propertyName = propertyName;
        this.value = value;
        this.compareType = compareType;
    }

    public Criterion createCriteria() {
        if (value == null) {
            return null;
        }
        return this;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CompareType getCompareType() {
        return this.compareType;
    }

    public void setCompareType(CompareType compareType) {
        this.compareType = compareType;
    }

}
