package pl.com.bernas.ioz.security.domain;

import java.util.HashSet;
import java.util.Set;

import pl.com.bernas.ioz.security.model.AuthorizedUser;
import pl.com.bernas.ioz.security.model.AuthorizedUserRole;

public class AuthorizedUserImpl implements AuthorizedUser {

	private static final long serialVersionUID = -8837205472320763401L;

	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private boolean online;

	private Set<AuthorizedUserRole> roles = new HashSet<AuthorizedUserRole>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@Override
	public String getName() {
		return null;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<AuthorizedUserRole> getRoles() {
		//return Collections.unmodifiableSet(roles);
		return roles;
	}

	public void setRoles(Set<AuthorizedUserRole> roles) {
		this.roles.clear();
		this.roles.addAll(roles);
	}

	public void addRole(AuthorizedUserRole role) {
		this.roles.add(role);
	}

	@Override
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
