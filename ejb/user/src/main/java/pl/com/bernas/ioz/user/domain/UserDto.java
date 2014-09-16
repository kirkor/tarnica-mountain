package pl.com.bernas.ioz.user.domain;

import java.util.Set;

import pl.com.bernas.tarnica.dto.AbstractDto;
import pl.com.bernas.tarnica.user.model.Role;
import pl.com.bernas.tarnica.user.model.User;

public class UserDto extends AbstractDto implements User {

	private static final long serialVersionUID = -3641512486897429661L;

	private int version;

	private String username;
	private String password;
	private boolean online;
	private UserDetailsDto details;
	private UserAddressDto address;
	private Set<Role> roles;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public UserDetailsDto getDetails() {
		return details;
	}

	public void setDetails(UserDetailsDto details) {
		this.details = details;
	}

	public UserAddressDto getAddress() {
		return address;
	}

	public void setAddress(UserAddressDto address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
