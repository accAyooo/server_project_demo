package com.accAyo.serverProjectDemo.dao;

import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:50 2018/5/10
 */
public interface IBaseDao<T> {

    public void save(T entity);

    public void update(T entity);

    public T findById(Integer id);

    public List<T> getList();
}
