package pl.beriko.ioz.web.remote.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import pl.com.bernas.ioz.security.service.AuthorizationService;

@ApplicationScoped
public class StatelessProducer extends RemoteAccess {

	public StatelessProducer() throws NamingException {
		super();
	}

	@Produces
	public AuthorizationService getAuthorizationService() {
		try {
			return (AuthorizationService) ctx.lookup("ejb:ioz-ear/ioz-security-services/authorizationService!pl.beriko.ioz.service.security.AuthorizationService");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
