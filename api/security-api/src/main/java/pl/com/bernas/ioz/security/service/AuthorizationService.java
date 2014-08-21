package pl.com.bernas.ioz.security.service;

import java.rmi.RemoteException;

import javax.security.auth.login.LoginException;

import pl.com.bernas.ioz.security.model.AuthorizedUser;

public interface AuthorizationService extends java.rmi.Remote {

    AuthorizedUser login(String userName, String password) throws LoginException, RemoteException;

    void logout(AuthorizedUser user);
}
