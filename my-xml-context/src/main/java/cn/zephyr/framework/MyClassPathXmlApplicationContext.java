package cn.zephyr.framework;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MyClassPathXmlApplicationContext
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/27 15:00
 */
public class MyClassPathXmlApplicationContext {

    private static Map<String,Object> beanMap = new HashMap<>();

    private static SAXReader saxReader = new SAXReader();

    private String xmlPath;

    public MyClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
        System.err.println("构造器初始化...");
    }

    public Object getBean(String beanName) throws Exception {
        if(StringUtils.isEmpty(beanName))
            throw new RuntimeException("bean name cannot be empty.");
        Object obj = beanMap.get(beanName);
        if(obj != null){
            return obj;
        }
        Document read = saxReader.read(new File(xmlPath));
        List<Element> elements = read.getRootElement().elements();
        if(!CollectionUtils.isEmpty(elements)){
            for (Element element : elements) {
                String beanId = element.attributeValue("id");
                String beanClass = element.attributeValue("name");
                if(!beanId.equals(beanName)){
                    continue;
                }
                Class<?> clazz = Class.forName(beanClass);
                Object beanObj = clazz.newInstance();
                beanMap.put(beanId,beanObj);
                return beanObj;
            }
            throw new RuntimeException("Bean config not found");
        }
        return null;
    }
}
