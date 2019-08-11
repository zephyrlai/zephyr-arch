package cn.zephyr.reflection;

import lombok.Data;

/**
 * @ClassName: DemoEntity
 * @Author: laizonghao
 * @Description: 实体
 * @Date: 2019-08-09 16:30
 */
@Data
public class DemoEntity {

    private Integer id;
    private String name;

    public DemoEntity() {
    }

    public DemoEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
