package org.bqftest.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodTest {
    /**
     * 反射(通过反射获取方法并使用)Method
     * Class.getMethod(String, Class...) 和 Class.getDeclaredMethod(String, Class...)
     * 方法可以获取类中的指定方法,调用invoke(Object, Object...)可以调用该方法,Class.getMethod("eat")
     * invoke(obj) Class.getMethod("eat",int.class) invoke(obj,10)*/
    public static void main(String[] args) throws Exception{
//        Class clazz=Class.forName("com.sanmao10.Person");
//        Constructor constructor=clazz.getConstructor(String.class,int.class);
//        Person p=(Person)constructor.newInstance("sanmao",25);
//
//        Method m=clazz.getMethod("papapa");            //获取papapa无参方法
//        m.invoke(p);
//
//        Method m1=clazz.getMethod("papapa",String.class,int.class); //获取有参的papapa方法
//        m1.invoke(p,"范冰冰",20);
        
//        调用静态方法
        Class<?> clz= Class.forName("jd.reflect.User");
        Method staticMethod= clz.getMethod("staticMethod", String.class);
        staticMethod.invoke(null, "hello");
        
        // 多个参数
        Class[] argClazz = new Class[]{User.class, String.class};
        Method m = clz.getMethod("test", argClazz);
        Object o = m.invoke(null, new User(), "dddd");
        System.out.println(o);
    }
}

class User {
    public static void staticMethod(String aaa) {
        System.out.println("static mthod invoke, args=" + aaa);
    }
    
    public static String test(User user, String aaa)
    {
        System.out.println("invoke test," + user.toString() + aaa);
        return user.toString() + aaa;
    }
}
