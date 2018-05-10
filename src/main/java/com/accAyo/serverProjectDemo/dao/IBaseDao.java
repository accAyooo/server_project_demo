package com.accAyo.serverProjectDemo.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:08 2018/5/10
 */
public interface IBaseDao<T> {

    /**
     * 根据ID查找实体
     *
     * @param clazz
     * @param id
     * @return
     */
    public T find(Class<T> clazz, int id);

    public void create(T t);

    public void save(T t);

    public void delete(T t);

    /**
     * 查询某页实体
     * @param hql 查询语句
     * @param firstResult 从第几行开始 索引从0开始
     * @param maxResult 最多返回的数据条数
     * @param map 参数键值对
     * @return
     */
    public List<T> list(String hql, int firstResult, int maxResult, Map<String, Object> map);

    public Query getQuery(String hql, Map<String, Object> map);

    /**
     * 查询实体
     * @param hql
     * @param map
     * @return
     */
    List<T> list(String hql, Map<String, Object> map);

    /**
     * 获取总记录数
     * @param hql
     * @param map
     * @return
     */
    int getTotalCount(String hql, Map<String, Object> map);
}

