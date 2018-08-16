package org.bqftest.spring.ioc;

public class DowJonesNewsListener implements IFXNewsListener {

	public void postProcessIfNecessary(String newsId){
	}

	public FXNewsBean getNewsByPK(String newsId) {
		System.out.println("invoke DowJonesNewsListener.getNewsByPK...");
		return null;
	}

	public String[] getAvailableNewsIds() {
		return null;
	}
}
