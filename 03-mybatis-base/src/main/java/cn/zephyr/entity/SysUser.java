package cn.zephyr.entity;

import lombok.Data;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-05 19:06
 */
@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;
}
