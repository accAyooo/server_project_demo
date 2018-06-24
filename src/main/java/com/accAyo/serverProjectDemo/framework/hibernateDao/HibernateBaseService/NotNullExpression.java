package com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:09 2018/5/17
 */
public class NotNullExpression implements Serializable, HibernateExpression {

    String propertyName;

    public NotNullExpression(String propertyName) {
        this.propertyName = propertyName;
    }

    public Criterion createCriteria() {
        return Restrictions.isNotNull(propertyName);
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
