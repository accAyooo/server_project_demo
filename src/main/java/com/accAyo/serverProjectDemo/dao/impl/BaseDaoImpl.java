package com.accAyo.serverProjectDemo.dao.impl;

import com.accAyo.serverProjectDemo.dao.IBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:30 2018/5/10
 */

@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public T find(Class<T> clazz, int id) {
        return (T) getSession().get(clazz, id);
    }

    @Override
    public void create(T t) {
        getSession().saveOrUpdate(t);
    }

    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public List<T> list(String hql, int firstResult, int maxResult, Map<String, Object> map) {
        Query query = getQuery(hql, map);
        List<T> list = query.setFirstResult(firstResult).setMaxResults(maxResult).list();
        return list;
    }

    @Override
    public Query getQuery(String hql, Map<String, Object> map) {
        Query query = getSession().createQuery(hql);
        if (map != null) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                Object value = map.get(key);
                if (value instanceof Collection<?>) {
                    query.setParameterList(key, (Collection[]) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(key, (Object[]) value);
                } else {
                    query.setParameter(key, value);
                }
            }
        }
        return query;
    }

    @Override
    public List<T> list(String hql, Map<String, Object> map) {
        Query query = getQuery(hql, map);
        List<T> list = query.list();
        return list;
    }

    @Override
    public int getTotalCount(String hql, Map<String, Object> map) {
        Query query = getQuery(hql, map);
        Object obj = query.uniqueResult();
        return ((Long) obj).intValue();
    }
}
