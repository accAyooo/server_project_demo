package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.service.IMemcachedService;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutionException;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:39 2018/5/25
 */

@Service
public class MemcachedService implements IMemcachedService, ConnectionObserver{

    private static final int EXP = 60 * 60 * 24 * 3; //过期时间三天

    private static String host = "127.0.0.1";
    private static String port = "11211";

    private MemcachedClient cache;

    @Override
    public void connectionEstablished(SocketAddress socketAddress, int i) {
        System.out.println("connectionEstablished...");
    }

    @Override
    public void connectionLost(SocketAddress socketAddress) {
        System.out.println("connectionLost...");
    }


    @PostConstruct
    public void init() {

        try {
            cache = new MemcachedClient(new InetSocketAddress(host, Integer.parseInt(port)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cache.addObserver(this);
    }

    @PreDestroy
    public void shutdown() {
        if (cache != null) {
            cache.shutdown();
            System.out.println("shutdown...");
        }
    }


    @Override
    public void set(String key, Object value) {
        set(key, value, EXP);
    }

    @Override
    public void set(String key, Object value, int exp) {
        if (cache == null || value == null) {
            return;
        }
        try {
            OperationFuture<Boolean> future = cache.set(key, exp, value);
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        if (cache == null) {
            return null;
        }

        try {
            return cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(String key) {
        if (cache == null)
            return;

        cache.delete(key);
    }
}
