package com.accAyo.serverProjectDemo.dao.impl;

import com.accAyo.serverProjectDemo.dao.IBaseDao;
import com.accAyo.serverProjectDemo.util.KernelUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:59 2018/5/8
 */
public class BaseDaoImpl<T> implements IBaseDao<T> {

    private Class<T> entityClass;
    private String hql;

    @Resource
    SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        // 通过反射获取类型的类对象
        this.entityClass = (Class<T>) KernelUtil.getSuperClassGenericType(getClass(), 0);
        this.hql = "from " + this.entityClass.getName();
    }


    @Override
    public void add(Object entity) throws Exception {
        this.getSession().save(entity);
    }

    @Override
    public void update(Object entity) throws Exception {
        this.getSession().update(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(Integer id) throws Exception {
        T result = (T) this.getSession().get(this.entityClass, id);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List getPageList(int startIndex, int pageSize) throws Exception {
        List<T> list = this.getSession().createQuery(hql).setFirstResult(startIndex)
                .setMaxResults(pageSize).list();
        return list;
    }

    @Override
    public long getTotal() {
        String sql = "select count(*) from " + this.entityClass.getName();
        long count = (Long) this.getSession().createQuery(sql).uniqueResult();
        return count;
    }
}
