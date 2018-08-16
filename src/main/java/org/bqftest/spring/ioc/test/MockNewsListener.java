package org.bqftest.spring.ioc.test;

import org.bqftest.spring.ioc.FXNewsBean;
import org.bqftest.spring.ioc.FXNewsRetrieveFailureException;
import org.bqftest.spring.ioc.IFXNewsListener;

public class MockNewsListener implements IFXNewsListener {
	
	public String[] getAvailableNewsIds() throws FXNewsRetrieveFailureException{
		throw new FXNewsRetrieveFailureException();
	}

	public FXNewsBean getNewsByPK(String newsId) {
		// TODO
		return null;
	}

	public void postProcessIfNecessary(String newsId) {
		// TODO
	}
}