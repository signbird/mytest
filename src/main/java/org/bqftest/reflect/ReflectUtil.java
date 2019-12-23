package org.bqftest.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * https://www.cnblogs.com/zhengtu2015/p/5829740.html
 */
public class ReflectUtil {

    public static void main(String[] args) throws Exception {
        Object[] methodArgs = new Object[]{"aaa"};
        invokeStaticMethod("jd.reflect.Test", "staticHello", methodArgs);
        
        Object[] consArgs = new Object[]{"consArgs"};
        Object owner = newInstance("jd.reflect.Test", consArgs);
        invokeMethod(owner, "hello", methodArgs);
        
        System.out.println(getProperty(owner, "sysParam"));
        
        
    }

    /**
     * 执行某个类的静态方法
     */
    public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(null, args);
    }

    /**
     * 执行某对象的方法
     */
    public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    }

    /**
     * 新建实例
     */
    public static Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }

    /**
     * 得到某个对象的所有属性
     */
    public static Object getProperty(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();
        Field field = ownerClass.getDeclaredField(fieldName);
        field.setAccessible(true);
//        Field f = ownerClass.getField(fieldName);
        Object property = field.get(owner);
        return property;
    }

    /**
     * 得到某个类的静态属性
     */
    public static Object getStaticProperty(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(ownerClass);
        return property;
    }
}

class Test {

    private String sysParam = "default";
    private static String staticParam = "staticDefault";

    public Test() {
    }

    public Test(String sysParam) {
        this.sysParam = sysParam;
    }

    public static void staticHello(String aa) {
        System.out.println("invoke staticHello, " + aa);
    }

    public String hello(String aa) {
        System.out.println("invoke non-static hello, " + aa + ", sysParam=" + sysParam);
        return sysParam;
    }

}
