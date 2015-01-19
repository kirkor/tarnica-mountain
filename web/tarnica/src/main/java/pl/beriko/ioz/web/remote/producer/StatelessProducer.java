package pl.beriko.ioz.web.remote.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import pl.com.bernas.ioz.security.service.AuthenticationService;

@ApplicationScoped
public class StatelessProducer extends RemoteAccess {

	public StatelessProducer() throws NamingException {
		super();
	}

	@Produces
	public AuthenticationService getAuthorizationService() {
		try {
			return (AuthenticationService) ctx.lookup("ejb:ioz-ear/ioz-security-services/authorizationService!pl.beriko.ioz.service.security.AuthorizationService");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
