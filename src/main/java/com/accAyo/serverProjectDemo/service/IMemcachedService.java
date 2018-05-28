package com.accAyo.serverProjectDemo.service;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:39 2018/5/25
 */
public interface IMemcachedService  {

    public void set(String key, Object value);

    public void set(String key, Object value, int exp);

    public Object get(String key);

    public void delete(String key);
}
