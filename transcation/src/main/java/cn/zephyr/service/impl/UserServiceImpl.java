package cn.zephyr.service.impl;

import cn.zephyr.annoation.MyTranscation;
import cn.zephyr.dao.UserDao;
import cn.zephyr.entity.User;
import cn.zephyr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 15:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.save(user);
//        int num = 1/0;
        userDao.save(new User("heihei",20));
    }

    @Override
    @MyTranscation
    public void addUser02(User user) {

        userDao.save(user);
//        int num = 1/0;
        userDao.save(new User("heihei",20));
    }


}
