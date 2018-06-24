package com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:54 2018/5/16
 */
public class BetweenExpression implements HibernateExpression, Serializable {

    private static final long serialVersionUID = 4989748515204188973L;

    String propertyName;
    Object lo;
    Object hi;

    /**
     *
     * @param propertyName
     * @param lo
     * @param hi
     */
    public BetweenExpression(String propertyName, Object lo, Object hi) {
        this.propertyName = propertyName;
        this.lo = lo;
        this.hi = hi;
    }

    public Criterion createCriteria() {
        return Restrictions.between(propertyName, lo, hi);
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getLo() {
        return this.lo;
    }

    public void setLo(Object lo) {
        this.lo = lo;
    }

    public Object getHi() {
        return this.hi;
    }

    public void setHi(Object hi) {
        this.hi = hi;
    }

}
