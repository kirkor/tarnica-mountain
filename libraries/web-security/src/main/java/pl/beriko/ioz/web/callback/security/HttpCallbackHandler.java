package pl.beriko.ioz.web.callback.security;

import javax.security.auth.callback.*;

/**
 * @author octavian.rusu
 * @version 1.0 Date: Mar 8, 2010
 */
public class HttpCallbackHandler implements CallbackHandler {

	private String name;
	private String password;

	public HttpCallbackHandler() {
		// TODO Auto-generated constructor stub
	}

	public HttpCallbackHandler(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public void handle(Callback[] callbacks) {
		// TODO: visitor
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback) {
				NameCallback nameCB = (NameCallback) callback;
				nameCB.setName(name);
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback passwordCB = (PasswordCallback) callback;
				passwordCB.setPassword(password.toCharArray());
			}
		}
	}

}
