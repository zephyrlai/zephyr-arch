package cn.zephyr.service;

import cn.zephyr.mapper.SysUserMapper;
import cn.zephyr.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 只有开启了事务，才是在同一个SqlSession实例；
     * 默认情况下（不开启事务），每个Mapper的每次查询操作都对应一个全新的SqlSession实例。
     * @return
     */
    @Transactional
    public List<SysUser> queryList(){
        /*System.err.println("===第一次查询===");
        sysUserMapper.queryList();
        System.err.println("===第二次查询===");
        sysUserMapper.queryList();
        System.err.println("===第三次查询===");
        sysUserMapper.queryList();*/
        return sysUserMapper.queryList();
    }
}
