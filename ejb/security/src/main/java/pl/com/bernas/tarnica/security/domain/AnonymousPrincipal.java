package pl.com.bernas.tarnica.security.domain;

import java.security.Principal;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;

public class AnonymousPrincipal implements Principal {

	@Override
	public String getName() {
		return AuthenticatedUser.ANONYMOUS;
	}

}
