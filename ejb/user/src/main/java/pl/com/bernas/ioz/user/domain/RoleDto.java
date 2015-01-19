package pl.com.bernas.ioz.user.domain;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import pl.com.bernas.tarnica.dto.AbstractDto;
import pl.com.bernas.tarnica.model.TarnicaEntity;
import pl.com.bernas.tarnica.user.model.Role;

public class RoleDto extends AbstractDto implements Role, TarnicaEntity {

	private static final long serialVersionUID = -8480739994130342811L;

	private String name;
	private Set<Principal> principals = new HashSet<Principal>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<Principal> getPrincipals() {
		return Collections.unmodifiableSet(principals);
	}

	public void setPermissions(Set<Principal> principals) {
		this.principals.clear();
		if (principals != null) {
			this.principals.addAll(principals);
		}
	}

	public void addPrincipals(Principal principals) {
		this.principals.add(principals);
	}
}
