package com.xuecheng.test.freemarker.controller;

import com.xuecheng.test.freemarker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;



@RequestMapping("/freemarker")
@Controller//不要使用RestController(json数据)
public class FreemarkerController {
    @Autowired
    RestTemplate restTemplate;

    //课程详情页面测试
    @RequestMapping("/course")
    public String course(Map<String,Object> map){
        ResponseEntity<Map> forEntity =
                restTemplate.getForEntity("http://localhost:31200/course/courseview/4028e581617f945f01617f9dabc40000", Map.class);
        Map body = forEntity.getBody();
        map.putAll(body);
        return "course";
    }

    @RequestMapping("/banner")
    public String index_banner(Map<String, Object> map){
        //使用restTemplate请求轮播图数据
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f", Map.class);
        Map body = forEntity.getBody();
        //设置数据
        map.putAll(body);
        return "index_banner";
    }
    //测试1
    @RequestMapping("/test1")
    public String test1(Map<String, Object> map){
        //map就是freemarker模板所使用的数据
        map.put("name","传智播客");

        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());

        //朋友列表
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        //给第二名学生设置朋友列表
        stu2.setFriends(friends);
        //给第二名学生设置一个最好的朋友
        stu2.setBestFriend(stu1);

        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //将学生列表放入数据模型中
        map.put("stus",stus);
        //准备map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        //向数据模型放数据
        map.put("stu1",stu1);
        //向数据模型放map数据
        map.put("stuMap",stuMap);

        map.put("point",102920122);

        //返回freemarker模板的位置，基于resources/templates路径
        return "test1";
    }
}
