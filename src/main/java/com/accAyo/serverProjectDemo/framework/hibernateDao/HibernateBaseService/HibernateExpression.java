package com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService;

import org.hibernate.criterion.Criterion;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:27 2018/5/16
 */
public interface HibernateExpression {
    public Criterion createCriteria();
}
