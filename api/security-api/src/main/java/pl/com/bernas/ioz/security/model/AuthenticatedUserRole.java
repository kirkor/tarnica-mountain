package pl.com.bernas.ioz.security.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

public interface AuthenticatedUserRole extends Principal, Serializable {
	public Set<Principal> getPrincipals();
}
