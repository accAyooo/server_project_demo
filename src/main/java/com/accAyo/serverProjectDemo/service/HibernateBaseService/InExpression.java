package com.accAyo.serverProjectDemo.service.HibernateBaseService;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:55 2018/5/16
 */
public class InExpression implements Serializable, HibernateExpression {

    private static final long serialVersionUID = 4736825656147322204L;

    Object[] values;
    String propertyName;

    public InExpression(String propertyName, Object[] values) {
        this.propertyName = propertyName;
        this.values = values;
    }

    public InExpression(String propertyName, Collection values) {
        this.propertyName = propertyName;

        if (values != null && values.size() > 1000) {

            Collection collection = new ArrayList();
            Iterator it = values.iterator();
            for (int i = 0; i < 1000; i++) {
                collection.add(it.next());
            }
            this.values = collection.toArray();

        }
        else {
            this.values = values.toArray();
        }

    }

    public Criterion createCriteria() {

        return Restrictions.in(propertyName, values);
    }

    public Object[] getValues() {
        return this.values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
