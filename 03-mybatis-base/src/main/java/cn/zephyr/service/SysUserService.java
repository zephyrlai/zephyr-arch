package cn.zephyr.service;

import cn.zephyr.mapper.SysUserMapper;
import cn.zephyr.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-05 19:09
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysUser> queryList(){
        return sysUserMapper.queryList();
    }
}
