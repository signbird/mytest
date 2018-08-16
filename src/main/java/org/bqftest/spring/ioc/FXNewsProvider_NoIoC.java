package org.bqftest.spring.ioc;

import org.apache.commons.lang3.ArrayUtils;

public class FXNewsProvider_NoIoC {
	private IFXNewsListener newsListener;
	private IFXNewsPersister newPersistener;

	/**
	 * 不使用Ioc的方式时，FXNewsProvider的构造需要依赖具体的依赖对象，跟具体对象DowJonesNewsListener绑定
	 * 
	 * 这时如果增加一种News如MarketWin24NewsListener，本类就没法使用了，只能重新实现FXNewsProvider，如 MarketWin24NewsProvider
	 */
	public FXNewsProvider_NoIoC() {
		newsListener = new DowJonesNewsListener();
		newPersistener = new DowJonesNewsPersister();
	}

	public void getAndPersistNews() throws FXNewsRetrieveFailureException{
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
}
