package pl.com.bernas.ioz.user.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import pl.com.bernas.tarnica.user.model.User;

public interface UserService<T extends User> {

	T findByUserName(String userName);
	
	T findById(Long pk);

	Long register(T user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	void online(T user);
	
	void offline(T user);

}
