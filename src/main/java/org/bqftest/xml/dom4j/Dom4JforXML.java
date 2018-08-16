package org.bqftest.xml.dom4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4JforXML {

    // @Test
    public void test() throws Exception {
        // 创建SAXReader对象 SAXReader就是一个管道，用一个流的方式，把xml文件读出来
        SAXReader reader = new SAXReader();
        // 读取文件 转换成Document
        Document document = reader.read(new File(this.getClass().getResource("").getPath() + "s1.xml"));
        // 获取根节点元素对象
        Element root = document.getRootElement();
        // 遍历
        listNodes(root);
    }

    @Test
    public void readStringXmlTest() {
        File f = new File(this.getClass().getResource("").getPath() + "s1.xml");
        // 直接读取文件时， 第一行的<?xml version="1.0" encoding="UTF-8"?> 会有问题  去掉这一行就行了
        readStringXml(txt2String(f));
    }

    /**
     * 读取txt文件的内容
     * 
     * @param file
     *            想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            // 使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // 遍历当前节点下的所有节点
    public void listNodes(Element node) {
        System.out.println("当前节点的名称：" + node.getName());
        // 首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        for (Attribute attribute : list) {
            System.out.println("属性" + attribute.getName() + ":" + attribute.getValue());
        }
        // 如果当前节点内容不为空，则输出
        if (!(node.getTextTrim().equals(""))) {
            System.out.println(node.getName() + "：" + node.getText());
        }
        // 同时迭代当前节点下面的所有子节点
        // 使用递归
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            listNodes(e);
        }
    }

    public void readStringXml(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);// 将字符串转为XML
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Element root = doc.getRootElement(); // 获取根节点
        listNodes(root);
    }
}
