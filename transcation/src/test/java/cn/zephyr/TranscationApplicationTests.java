package cn.zephyr;

import cn.zephyr.entity.User;
import cn.zephyr.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranscationApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){
        userService.addUser(new User("haha",1));
    }

    @Test
    public void saveUser02Test(){
        userService.addUser02(new User("haha",19));
    }

}
