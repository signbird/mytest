package org.bqftest.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程接口
 */
public interface AddService extends Remote
{
	public int addNumbers(int firstnumber, int secondnumber) throws RemoteException;
}