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
        CarModel carModel = new BMWCarModel();
        carModel.setSequenceList(sequenceList);
        carModel.run();

        sequenceList.clear();
        sequenceList.add("light");
        sequenceList.add("start");
        sequenceList.add("stop");
        carModel = new TeslaCarModel();
        carModel.setSequenceList(sequenceList);
        carModel.run();
    }
}
