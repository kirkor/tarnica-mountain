package pl.com.bernas.tarnica.security.domain;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import pl.com.bernas.ioz.security.model.AuthorizedUserRole;

public class AuthorizedUserRoleImpl implements AuthorizedUserRole {

	private static final long serialVersionUID = 6513439953345886263L;

	private String name;
	private List<Permission> permissions = new ArrayList<Permission>();

	public AuthorizedUserRoleImpl() {
	}

	// public AuthorizedRole(RoleEntity roleEntity) {
	// this.name = roleEntity.getName();
	// for (PermissionEntity pe : roleEntity.getPermissions()) {
	// TODO: DOZER + DTO addPermission(pe.toPermision());
	// }
	// }

	public AuthorizedUserRoleImpl(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (this.name == null) {
			this.name = name;
		}
	}

	public List<Permission> getPermissions() {
		// return Collections.unmodifiableList(permissions);
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions.clear();
		if (permissions != null) {
			this.permissions.addAll(permissions);
		}
	}

	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}

}
