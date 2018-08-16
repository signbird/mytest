package org.bqftest.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程接口实现类
 * @author 95
 *
 */
public class AddServiceImpl extends UnicastRemoteObject implements AddService
{
	private static final long serialVersionUID = 1L;

	public AddServiceImpl() throws RemoteException
	{
		super();
	}

	public int addNumbers(int firstnumber, int secondnumber) throws RemoteException
	{
		return firstnumber + secondnumber;
	}
}
