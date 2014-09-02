package pl.com.bernas.ioz.user.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pl.com.bernas.tarnica.model.AbstractTarnicaEntity;
import pl.com.bernas.tarnica.user.model.User;

/**
 * User: iru Date: Feb 10, 2010 Time: 3:27:15 PM
 */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
@Cacheable
public class UserEntity extends AbstractTarnicaEntity implements User {

	private static final long serialVersionUID = 5479375943306863580L;

	private String username;
	private String password;
	private String email;
	private boolean online;
	private UserDetailsEntity details;
	private UserAddressEntity address;
	private Set<RoleEntity> roles;

	@Column(name = "username", nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "is_online", nullable = false, length = 1)
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Embedded
	public UserDetailsEntity getDetails() {
		return details;
	}

	public void setDetails(UserDetailsEntity details) {
		this.details = details;
	}

	@Embedded
	public UserAddressEntity getAddress() {
		return address;
	}

	public void setAddress(UserAddressEntity address) {
		this.address = address;
	}

	@ManyToMany(targetEntity = RoleEntity.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_to_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Fetch(value = FetchMode.SUBSELECT)
	// FIX: HHH-1718
	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	@PrePersist
	void onCreate() {
		this.setVersion(1);
	}

	@PreUpdate
	void onPersist() {
		this.setVersion(this.getVersion() + 1);
	}
}
