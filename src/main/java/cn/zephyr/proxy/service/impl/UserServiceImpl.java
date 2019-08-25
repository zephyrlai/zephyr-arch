package cn.zephyr.proxy.service.impl;

import cn.zephyr.proxy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/24 17:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean insert(Object object) {
        System.out.println("===执行sql insert===");
        return true;
    }
}
