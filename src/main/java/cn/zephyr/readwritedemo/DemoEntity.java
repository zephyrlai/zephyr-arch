package cn.zephyr.readwritedemo;

/**
 * @ClassName: DemoEntity
 * @Author: laizonghao
 * @Description: 实体类
 * @Date: 2019-08-01 09:35
 */
public class DemoEntity {
    private String name;
    private String gender;

    private Boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
