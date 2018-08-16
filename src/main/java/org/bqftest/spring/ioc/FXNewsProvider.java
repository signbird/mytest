package org.bqftest.spring.ioc;

import org.apache.commons.lang3.ArrayUtils;

public class FXNewsProvider {
	private IFXNewsListener newsListener;
	private IFXNewsPersister newPersistener;

//	public FXNewsProvider(){}
	
	/**
	 * 通过构造方法注入 （Constructor Injection），实现IOC
	 * 还可以通过get set方法注入、接口注入
	 */
	public FXNewsProvider(IFXNewsListener newsListner, IFXNewsPersister newsPersister) {
		this.newsListener = newsListner;
		this.newPersistener = newsPersister;
	}

	public void getAndPersistNews() throws FXNewsRetrieveFailureException {
		System.out.println("invoke FXNewsProvider.getAndPersistNews...");
		String[] newsIds = newsListener.getAvailableNewsIds();
		if (ArrayUtils.isEmpty(newsIds)) {
			return;
		}
		for (String newsId : newsIds) {
			FXNewsBean newsBean = newsListener.getNewsByPK(newsId);
			newPersistener.persistNews(newsBean);
			newsListener.postProcessIfNecessary(newsId);
		}
	}

//	public void setNewsListener(IFXNewsListener newsListener) {
//		this.newsListener = newsListener;
//	}
//
//	public void setNewPersistener(IFXNewsPersister newPersistener) {
//		this.newPersistener = newPersistener;
//	}
}
