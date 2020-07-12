package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //创建jwt令牌
    @Test
    public void testRedis(){
        //定义key
        String key = "user_token:0caf943e-4973-4d85-af33-3fbea2d33376";
        //定义value
        Map<String,String> value = new HashMap<>();
        value.put("jwt","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU5NDU3ODE5MCwianRpIjoiMGNhZjk0M2UtNDk3My00ZDg1LWFmMzMtM2ZiZWEyZDMzMzc2IiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.CS32Sa3pBHaCW_Mgl2Xx5TUr5kiFHwZkcgwv4i5YTjS1Zjm-kjDhdZjLDBRXKxZewOMGhegBbeSPeYCnzcUKzhMCPhIUZVc2l4cT8P1cirtB90DnJY-qs4rsN-rXuUfUu2ZI1tRlI0FADzKLBQ0pGRHt0kNzZNZ0wK8h3COZ_hj8mIM5Mg1v-hwA62hWbaDao9GOar1xUbVYp_xnpj7uKN8f8YqbgFepDzXfBuHSOU0T6zM08altbXrQ7k4t1jgzKV7yMD2hENW9M24WgG4rk282MIwduoQGeFcWY5r2jcgu5JGQoBK5vPAQ8pYxTUibOgQe0exfeE-kcP-LrjxOzw");
        value.put("refresh_token","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJhdGkiOiIwY2FmOTQzZS00OTczLTRkODUtYWYzMy0zZmJlYTJkMzMzNzYiLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU5NDU3ODE5MCwianRpIjoiZGRmYWNmYjMtMjNjNy00NDgxLTk5YmEtM2RiNjc2OTZiMTA2IiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.b_ADp4yzvIegxbWcb0d2fqgQW2w_4RkQHFPvsFh5OZyIXtffjM0VG8xiZQkfy1BuWGyEokMpM6_i0wB5ux1IYjZaLwq-IAGIWpzx8vTsKNidAjUc1wKo3szTNC6B77uG7XD_tceDBgzeo6RRa4c9_VP8rFNR7WmX2yuGNsjnRFnop0BI20z6RzjMN2_hkrDcnFLDiYg2KK-u1l7zsv4HL8rniiM0cNVfMpnZKtguldxD_pQZY0qal2fgidvHvrgdrihB4VbRwW3WkEjSShywHfUtOTsGj_xUJlHeorECRTth1gR1y1FmlFMrxFBuZf7g-92Xk0kB2lFwa54y18nENg");
        String jsonString = JSON.toJSONString(value);
        //校验key是否存在，如果不存在则返回-2
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        System.out.println(expire);
        //存储数据
        stringRedisTemplate.boundValueOps(key).set(jsonString,30, TimeUnit.SECONDS);
        //获取数据
        String string = stringRedisTemplate.opsForValue().get(key);
        System.out.println(string);


    }


}
