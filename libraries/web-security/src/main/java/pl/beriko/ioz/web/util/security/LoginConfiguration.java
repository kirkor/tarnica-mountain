package pl.beriko.ioz.web.util.security;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;
import pl.com.bernas.ioz.security.service.AuthenticationService;

public class LoginConfiguration extends Configuration {

	@Inject
	private AuthenticationService authenticationService;
	private AuthenticatedUser authenticatedUser;

	public LoginConfiguration(AuthenticationService authenticationService, AuthenticatedUser authenticatedUser) {
		this.authenticationService = authenticationService;
		this.authenticatedUser = authenticatedUser;
	}

	public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("authenticationService", authenticationService);
		values.put("authenticatedUser", authenticatedUser);
		AppConfigurationEntry ace = new AppConfigurationEntry(LoginModuleImpl.class.getName(), AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, values);
		return new AppConfigurationEntry[] { ace };
	}
}
