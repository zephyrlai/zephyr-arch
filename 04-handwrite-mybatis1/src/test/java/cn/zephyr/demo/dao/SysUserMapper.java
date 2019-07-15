package cn.zephyr.demo.dao;

import cn.zephyr.framework.SysUser;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-15 11:52
 */
public interface SysUserMapper {

    SysUser selectByPrimaryKey(Integer id);
}
