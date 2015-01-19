package pl.com.bernas.tarnica.security.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;
import pl.com.bernas.ioz.security.model.AuthenticatedUserRole;

public class AuthenticatedUserImpl implements AuthenticatedUser {

	private static final long serialVersionUID = 7340744009332436125L;

	protected Long id;
	protected String username;
	private String firstName;
	private String lastName;
	protected boolean online;
	protected Set<AuthenticatedUserRole> roles = new HashSet<AuthenticatedUserRole>();

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Set<AuthenticatedUserRole> getRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public void setRoles(Set<AuthenticatedUserRole> roles) {
		this.roles.clear();
		this.roles.addAll(roles);
	}

	public void addRole(AuthenticatedUserRole role) {
		this.roles.add(role);
	}

	@Override
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", id).append("username", username).append("isOnline", online).append("roles", roles).toString();
	}
}
