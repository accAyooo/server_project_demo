package com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:12 2018/5/17
 */
public class NullExpression implements HibernateExpression, Serializable {

    String propertyName;

    public NullExpression(String propertyName) {
        this.propertyName = propertyName;
    }

    public Criterion createCriteria() {

        return Restrictions.isNull(propertyName);
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
