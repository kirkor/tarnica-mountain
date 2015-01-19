package pl.com.bernas.tarnica.security.domain;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;

public class AnonymousUser extends AuthenticatedUserImpl implements AuthenticatedUser {

	private static final long serialVersionUID = -4067862332160914947L;

	public AnonymousUser() {
		setId(-1L);
		setUsername(AuthenticatedUser.ANONYMOUS);
		addRole(new AnonymousRole());
	}
}
