package cn.zephyr.demo;

import cn.zephyr.demo.dao.SysUserMapper;
import cn.zephyr.framework.MyConfiguration;
import cn.zephyr.framework.MyDefaultExector;
import cn.zephyr.framework.MySqlSession;
import cn.zephyr.framework.SysUser;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-15 12:01
 */
public class MybatisTest {
    public static void main(String[] args) {
        MySqlSession mySqlSession = new MySqlSession(new MyConfiguration(), new MyDefaultExector());
        SysUserMapper mapper = mySqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = mapper.selectByPrimaryKey(1);
        System.err.println(sysUser.toString());
    }
}
