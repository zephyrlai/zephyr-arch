package cn.zephyr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.zephyr.entity.User;

/**
 * @ClassName: UserDao
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 15:54
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

}
