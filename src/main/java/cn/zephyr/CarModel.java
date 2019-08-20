package cn.zephyr;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CarModel
 * @Author: laizonghao
 * @Description: 上层产品类
 * @Date: 2019-08-20 14:37
 */
public abstract class CarModel {

    private List<String> sequenceList = new ArrayList<String>();

    public abstract void start();

    public abstract void light();

    public abstract void stop();

    public final void run(){
        for (String s : sequenceList) {
            if(s.equals("start")){
                this.start();
            }else if(s.equals("light")){
                this.light();
            }else if(s.equals("stop")){
                this.stop();
            }
        }
    }

    public void setSequenceList(List<String> sequenceList) {
        this.sequenceList = sequenceList;
    }
}
