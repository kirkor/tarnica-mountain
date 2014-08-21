package pl.beriko.ioz.web.util.security;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import pl.com.bernas.ioz.security.model.AuthorizedUser;
import pl.com.bernas.ioz.security.service.AuthorizationService;

public class LoginConfiguration extends Configuration {

	@Inject
	private AuthorizationService authenticationService;
	private AuthorizedUser user;

	public LoginConfiguration(AuthorizationService authenticationService, AuthorizedUser user) {
		this.authenticationService = authenticationService;
		this.user = user;
	}

	public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("authenticationService", authenticationService);
		values.put("user", user);
		AppConfigurationEntry ace = new AppConfigurationEntry(LoginModuleImpl.class.getName(), AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, values);
		return new AppConfigurationEntry[] { ace };
	}
}
