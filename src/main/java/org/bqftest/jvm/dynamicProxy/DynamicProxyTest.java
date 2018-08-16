package org.bqftest.jvm.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest
{

	interface IHello
	{
		void sayHello();
	}
	
	static class Hello implements IHello
	{
		@Override
        public void sayHello()
        {
	        System.out.println("Hello world.");
        }
	}
	
	static class DynamicProxy implements InvocationHandler
	{
		Object originalObj;
		
		Object bind(Object obj)
		{
			this.originalObj = obj;
			return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
		}
		
		@Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
			System.out.println("welcome");
	        return method.invoke(originalObj, args);
        }
	}
	
	public static void main(String[] args)
    {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
	    IHello hello = (IHello) new DynamicProxy().bind(new Hello());
	    hello.sayHello();
    }
}
