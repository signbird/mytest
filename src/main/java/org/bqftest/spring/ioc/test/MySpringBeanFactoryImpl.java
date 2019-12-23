package org.bqftest.spring.ioc.test;

import org.bqftest.spring.ioc.DowJonesNewsListener;
import org.bqftest.spring.ioc.DowJonesNewsPersister;
import org.bqftest.spring.ioc.FXNewsProvider;
import org.bqftest.spring.ioc.FXNewsRetrieveFailureException;
import org.bqftest.spring.ioc.IFXNewsListener;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 通过直接编码的方式，完成BeanFactory的对象注册与依赖绑定
 * 可以参考spring的DefaultListableBeanFactoryTests
 */
public class MySpringBeanFactoryImpl {

	public static void main(String[] args) throws FXNewsRetrieveFailureException {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = (BeanFactory) bindViaCode(beanRegistry);
		FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("myNewsProvider");
		newsProvider.getAndPersistNews(); 
		
		IFXNewsListener myListener = (IFXNewsListener)container.getBean("myListener");
		myListener.getNewsByPK("newsId");
	}

	/**
	 * 通过直接编码的方式，完成BeanFactory的对象注册与依赖绑定
	 */
	public static BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
		// 使用RootBeanDefinition 作 为 BeanDefinition 的 实 现 类
		AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class,
				AutowireCapableBeanFactory.AUTOWIRE_NO, true);
		AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class,
				AutowireCapableBeanFactory.AUTOWIRE_NO, true);
		AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class,
				AutowireCapableBeanFactory.AUTOWIRE_NO, true);

		// 将bean定义注册到容器中
		registry.registerBeanDefinition("myNewsProvider", newsProvider);
		registry.registerBeanDefinition("myListener", newsListener);
		registry.registerBeanDefinition("myPersister", newsPersister);

		// 指定依赖关系
		// 1. 可以通过构造方法注入方式（业务类FXNewsProvider中需要定义相应的构造函数）
		ConstructorArgumentValues argValues = new ConstructorArgumentValues();
		argValues.addIndexedArgumentValue(0, newsListener);
		argValues.addIndexedArgumentValue(1, newsPersister);
		newsProvider.setConstructorArgumentValues(argValues);
		
		 // 2. 或者通过setter方法注入方式
		// (业务类FXNewsProvider中需要定义newsListener和newPersistener的setter方法,
		// 同时还要提供默认构造函数，否则报 NoSuchMethodException: FXNewsProvider.<init>())
//		MutablePropertyValues propertyValues = new MutablePropertyValues();
//		propertyValues.addPropertyValue(new PropertyValue("newsListener", newsListener));
//		propertyValues.addPropertyValue(new PropertyValue("newPersistener", newsPersister));
//		newsProvider.setPropertyValues(propertyValues);

		// 绑定完成
		return (BeanFactory) registry;
	}
}
