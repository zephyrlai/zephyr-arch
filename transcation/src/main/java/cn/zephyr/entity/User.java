package cn.zephyr.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: User
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/26 15:53
 */
@Data
@Table(name = "sys_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
