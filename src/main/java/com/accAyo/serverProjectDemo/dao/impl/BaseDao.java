package com.accAyo.serverProjectDemo.dao.impl;

import com.accAyo.serverProjectDemo.dao.IBaseDao;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:51 2018/5/10
 */
@Transactional
public class BaseDao<T> implements IBaseDao<T> {

    @Resource
    private HibernateTemplate hibernateTemplate;

    private Class<T> clazz;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public Session getSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    public BaseDao(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        if (type instanceof Class) {
            clazz = (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            clazz = (Class<T>) ((ParameterizedType)type).getRawType();
        }

    }

    @Override
    public void save(T entity) {
        getSession().save(entity);

    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public T findById(Integer id) {
        return getSession().get(clazz, id);
    }

    @Override
    public List<T> getList() {
        return getSession().createQuery("from" + clazz.getSimpleName()).list();
    }
}
