package org.bqftest.spring.ioc.test;

import org.bqftest.spring.ioc.FXNewsProvider;
import org.bqftest.spring.ioc.FXNewsRetrieveFailureException;

import junit.framework.TestCase;

/**
 *  该测试用例证明， 
 *  1、确实可以通过构造函数注入，创建出业务对象FXNewsProvider_IoC，而不用修改FXNewsProvider_IoC代码
 *  2、依赖对象MockNewsListener可以作为测试使用， 方便进行单元测试   而不需要依赖具体的某个对象如DowJonesNewsListener，减少了测试依赖
 *
 */
public class FXNewsProviderTest extends TestCase {
	private FXNewsProvider newsProvider;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		newsProvider = new FXNewsProvider(new MockNewsListener(), new MockNewsPersister());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		newsProvider = null;
	}

	public void testGetAndPersistNewsWithoutResourceAvailable() {
		try {
			newsProvider.getAndPersistNews();
			fail("Since MockNewsListener has no news support, we should fail to get above.");
		} catch (FXNewsRetrieveFailureException e) {
			// ……
			System.out.println("OK, got exception");
		}
	}
}