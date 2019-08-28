package cn.zephyr;

import cn.zephyr.framework.MyApplicationContext;
import cn.zephyr.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MyAnnotationContextApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void userServiceTest(){
        MyApplicationContext applicationContext = new MyApplicationContext("cn.zephyr");
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.queryUser();
    }


}
