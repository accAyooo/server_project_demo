package com.accAyo.serverProjectDemo.service.HibernateBaseService;

import com.accAyo.serverProjectDemo.common.LogicalType;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:09 2018/5/17
 */
public class LogicalExpression extends
        org.hibernate.criterion.LogicalExpression implements
        HibernateExpression, Serializable {

    private static final long serialVersionUID = -2181784131598091834L;

    private HibernateExpression lhs;

    private HibernateExpression rhs;

    private LogicalType type;

    public LogicalExpression(HibernateExpression lhs, HibernateExpression rhs,
                             LogicalType type) {
        super(lhs.createCriteria(), rhs.createCriteria(), type.getValue());
        setLhs(lhs);
        setRhs(rhs);
        setType(type);
    }

    public Criterion createCriteria() {
        return this;
    }

    public HibernateExpression getLhs() {
        return this.lhs;
    }

    public void setLhs(HibernateExpression lhs) {
        this.lhs = lhs;
    }

    public HibernateExpression getRhs() {
        return this.rhs;
    }

    public void setRhs(HibernateExpression rhs) {
        this.rhs = rhs;
    }

    public LogicalType getType() {
        return this.type;
    }

    public void setType(LogicalType type) {
        this.type = type;
    }

}
