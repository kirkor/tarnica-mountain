package pl.com.bernas.tarnica.security.domain;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.com.bernas.ioz.security.model.AuthenticatedUserRole;

public class AuthenticatedUserRoleImpl implements AuthenticatedUserRole {

	private static final long serialVersionUID = 6513439953345886263L;

	private String name;
	private Set<Principal> principals = new HashSet<>();

	public AuthenticatedUserRoleImpl(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Set<Principal> getPrincipals() {
		return Collections.unmodifiableSet(principals);
	}

	public void setPrincipals(Set<Principal> principals) {
		this.principals.clear();
		if (principals != null) {
			this.principals.addAll(principals);
		}
	}

	public void addPrincipal(Principal principal) {
		this.principals.add(principal);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("name", getName()).append("principals", getPrincipals()).toString();
	}

}
