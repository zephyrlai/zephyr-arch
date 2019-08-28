package cn.zephyr;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: Dom4jDemo
 * @Author: laizonghao
 * @Description: xml解析demo
 * @Date: 2019/8/27 11:21
 */
public class Dom4jDemo {

    public static void main(String[] args) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/main/resources/student.xml"));
        Element rootElement = document.getRootElement();
        getNode(rootElement);
     }

    public static void getNode(Element... rootElement){
        for (Element element : rootElement) {
            System.out.println("=======================");
            System.err.println("节点名称"+element.getName());
            List<Attribute> attributes = element.attributes();
            System.err.println("属性列表"+ Arrays.asList(attributes));
            String textTrim = element.getTextTrim();
            System.err.println("节点内容："+textTrim);
            List<Element> elements = element.elements();
            if(elements != null && elements.size()>0){
                getNode(elements.toArray(new Element[elements.size()]));
            }

        }
    }
}
