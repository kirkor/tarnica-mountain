package pl.com.bernas.tarnica.security.domain;

import pl.com.bernas.ioz.security.model.AuthorizedUserRole;

public class AnonymousRole extends AuthorizedUserRoleImpl implements AuthorizedUserRole {

	private static final long serialVersionUID = 2787351058977978974L;

	public AnonymousRole() {
		super("ANONYMOUS");
	}
}
