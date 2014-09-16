package pl.com.bernas.ioz.user.model;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pl.com.bernas.tarnica.model.AbstractTarnicaEntity;
import pl.com.bernas.tarnica.user.model.Role;
import pl.com.bernas.tarnica.user.model.User;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
@Cacheable
@Access(AccessType.FIELD)
public class UserEntity extends AbstractTarnicaEntity implements User {

	private static final long serialVersionUID = 5479375943306863580L;

	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "is_online", nullable = false, length = 1)
	private boolean online;
	@Embedded
	private UserDetailsEntity details;
	@Embedded
	private UserAddressEntity address;
	@ManyToMany(targetEntity = RoleEntity.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_to_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Fetch(value = FetchMode.SUBSELECT)
	// FIX: HHH-1718
	private Set<Role> roles;	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public UserDetailsEntity getDetails() {
		return details;
	}

	public void setDetails(UserDetailsEntity details) {
		this.details = details;
	}

	public UserAddressEntity getAddress() {
		return address;
	}

	public void setAddress(UserAddressEntity address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
