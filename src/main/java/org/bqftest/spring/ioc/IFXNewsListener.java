package org.bqftest.spring.ioc;

public interface IFXNewsListener {

	void postProcessIfNecessary(String newsId);

	FXNewsBean getNewsByPK(String newsId);

	String[] getAvailableNewsIds() throws FXNewsRetrieveFailureException;

}
