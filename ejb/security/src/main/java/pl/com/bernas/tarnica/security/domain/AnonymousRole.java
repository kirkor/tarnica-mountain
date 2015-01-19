package pl.com.bernas.tarnica.security.domain;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;
import pl.com.bernas.ioz.security.model.AuthenticatedUserRole;

public class AnonymousRole extends AuthenticatedUserRoleImpl implements AuthenticatedUserRole {

	private static final long serialVersionUID = 2787351058977978974L;

	public AnonymousRole() {
		super(AuthenticatedUser.ANONYMOUS);
		
		addPrincipal(new AnonymousPrincipal());
	}
}
