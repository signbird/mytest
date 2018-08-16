package org.bqftest.xml.dom4j;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * http://blog.csdn.net/chenghui0317/article/details/11486271
 * 
 * @author Administrator
 *
 */
public class ParseDemo {

    public static void main(String[] args) {
        ParseDemo p = new ParseDemo();
        p.parseXml03();
    }

    public void parseXml03() {
        try {
            // 将src下面的xml转换为输入流
            InputStream inputStream = this.getClass().getResourceAsStream("module03.xml");
            // 创建SAXReader读取器，专门用于读取xml
            SAXReader saxReader = new SAXReader();
            // 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
            Document document = saxReader.read(inputStream);

            Element rootElement = document.getRootElement();
            if (rootElement.elements("module") != null) {
                // 因为第一个module标签只有内容没有子节点，直接.iterator()就java.lang.NullPointerException了, 所以需要分开实现
                List<Element> elementList = rootElement.elements("module");
                for (Element element : elementList) {

                    if (element.elements().size() == 0) {
                        System.out.println("【1】" + element.getTextTrim());
                    } else {
                        if (element.attribute(0) != null) {
                            System.out.println(
                                    "   【2】" + element.attribute(0).getName() + ":" + element.attribute(0).getValue());
                        }
                        Element nameElement = element.element("name");
                        System.out.println("   【2】" + nameElement.getName() + ":" + nameElement.getText());
                        Element valueElement = element.element("value");
                        System.out.println("   【2】" + valueElement.getName() + ":" + valueElement.getText());
                        Element descriptElement = element.element("descript");
                        System.out.println("   【2】" + descriptElement.getName() + ":" + descriptElement.getText());

                        List<Element> subElementList = element.elements("module");
                        for (Element subElement : subElementList) {
                            if (subElement.elements().size() == 0) {
                                System.out.println("      【3】" + subElement.getTextTrim());
                            } else {
                                Element subnameElement = subElement.element("name");
                                System.out.println(
                                        "      【3】" + subnameElement.getName() + ":" + subnameElement.getText());
                                Element subvalueElement = subElement.element("value");
                                System.out.println(
                                        "      【3】" + subvalueElement.getName() + ":" + subvalueElement.getText());
                                Element subdescriptElement = subElement.element("descript");
                                System.out.println("      【3】" + subdescriptElement.getName() + ":"
                                        + subdescriptElement.getText());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
