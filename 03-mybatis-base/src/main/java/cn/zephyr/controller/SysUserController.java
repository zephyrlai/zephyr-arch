package cn.zephyr.controller;

import cn.zephyr.entity.SysUser;
import cn.zephyr.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-05 19:10
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("queryList")
    public List<SysUser> queryList(){
        return sysUserService.queryList();
    }


}
