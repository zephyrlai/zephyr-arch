package cn.zephyr.disruptor;

/**
 * @ClassName: MyEvent
 * @Author: laizonghao
 * @Description: 自定义事件
 * @Date: 2019-08-07 11:13
 */
public class MyEvent {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
