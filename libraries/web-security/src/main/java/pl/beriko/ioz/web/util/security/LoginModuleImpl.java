package pl.beriko.ioz.web.util.security;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import pl.beriko.ioz.web.util.SecurityUtil;
import pl.com.bernas.ioz.security.model.AuthorizedUser;
import pl.com.bernas.ioz.security.model.AuthorizedUserRole;
import pl.com.bernas.ioz.security.service.AuthorizationService;

public class LoginModuleImpl implements LoginModule {

	private Context ctx;
	private final static String AUTH_BEAN = "ejb:ioz-ear/ioz-security-services/authorizationService!pl.beriko.ioz.service.security.AuthorizationService";

	private AuthorizationService authenticationService;
	private Subject subject;
	private CallbackHandler callbackHandler;
	private AuthorizedUser user;

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;

		Properties jndiProps = new Properties();
		jndiProps.put("jboss.naming.client.ejb.context", true);
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		try {
			this.ctx = new InitialContext(jndiProps);

			this.authenticationService = (AuthorizationService) this.ctx.lookup(LoginModuleImpl.AUTH_BEAN);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}

	}

	public boolean login() throws LoginException {
		NameCallback nameCallback = new NameCallback("authController.password");
		PasswordCallback passwordCallback = new PasswordCallback("authController.password", false);
		Callback[] callbacks = new Callback[] { nameCallback, passwordCallback };
		try {
			callbackHandler.handle(callbacks);
		} catch (IOException e) {
			e.printStackTrace();
			LoginException ex = new LoginException("IOException logging in.");
			ex.initCause(e);
			throw ex;
		} catch (UnsupportedCallbackException e) {
			String className = e.getCallback().getClass().getName();
			LoginException ex = new LoginException(className + " is not a supported Callback.");
			ex.initCause(e);
			throw ex;
		}
		String userName = nameCallback.getName();
		String password = String.valueOf(passwordCallback.getPassword());
		try {
			user = authenticationService.login(userName, password);

			if (user == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	public boolean commit() throws LoginException {
		this.subject.getPublicCredentials().add(this.user);
		this.subject.getPrincipals().add(this.user);
		
		if (!this.subject.getPrincipals().contains(this.user)) {
            this.subject.getPrincipals().add(this.user);
            for (AuthorizedUserRole role : user.getRoles()) {
    			this.subject.getPrincipals().add(role);
    		}
        }		

		SecurityUtil.propagateSubject(this.subject);
		return true;
	}

	public boolean abort() throws LoginException {
		subject.getPublicCredentials().clear();
		subject.getPrincipals().clear();

		// subject.getPrincipals().add(AppUtil.getAnonymousRole());
		user = null;
		return true;
	}

	public boolean logout() throws LoginException {
		try {
			authenticationService.logout(user);
			return true;
		} catch (Exception e) {
			throw new LoginException("Can not execute logout process!!!");
		} finally {
			subject.getPublicCredentials().clear();
			subject.getPrincipals().clear();
			// subject.getPrincipals().add(AppUtil.getAnonymousRole());
		}
	}
}
