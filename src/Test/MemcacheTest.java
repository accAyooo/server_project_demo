import com.danga.MemCached.MemCachedClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午5:41 2018/5/27
 */

public class MemcacheTest{

    private MemCachedClient client;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        client = (MemCachedClient) context.getBean("memcachedClient");
    }

    @Test
    public void test(){
        client.set("name", "shixiangyu");
        System.out.println(client.get("name"));
    }
 }
