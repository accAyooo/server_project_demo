package com.accAyo.serverProjectDemo.dao;

import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:59 2018/5/8
 */
public interface IBaseDao<T> {

    public void add(T entity) throws Exception;

    public void update(T entity) throws Exception;

    public T findById(Integer id) throws Exception;

    public List<T> getPageList(int startPage, int pageSize) throws Exception;

    public long getTotal();
}
