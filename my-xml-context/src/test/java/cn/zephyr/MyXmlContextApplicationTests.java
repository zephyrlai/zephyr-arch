package cn.zephyr;

import cn.zephyr.framework.MyClassPathXmlApplicationContext;
import cn.zephyr.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MyXmlContextApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void myClassPathXmlContextTest() throws Exception {
        MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext("src/main/resources/mySpring.xml");
        UserService userService = (UserService)context.getBean("userService");
        userService = (UserService)context.getBean("userService");
        userService = (UserService)context.getBean("userService");
        userService = (UserService)context.getBean("userService");
        userService = (UserService)context.getBean("userService");
        userService.add();
        userService = (UserService)context.getBean("userService1");
        userService.add();
    }

}
