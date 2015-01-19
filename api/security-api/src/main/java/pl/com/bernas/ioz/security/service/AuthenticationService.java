package pl.com.bernas.ioz.security.service;

import java.rmi.RemoteException;

import javax.security.auth.login.LoginException;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;

public interface AuthenticationService extends java.rmi.Remote {

    AuthenticatedUser login(String userName, String password) throws LoginException, RemoteException;

    void logout(AuthenticatedUser authenticatedUser);
}
