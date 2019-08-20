package cn.zephyr;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Client
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-20 14:44
 */
public class Client {

    public static void main(String[] args) {
        List<String> sequenceList = new ArrayList<String>();
        sequenceList.add("start");
        sequenceList.add("light");
        sequenceList.add("stop");
        BMWCarModel bmwCarModel = new BMWCarModel();
        bmwCarModel.setSequenceList(sequenceList);
        bmwCarModel.run();
    }
}
