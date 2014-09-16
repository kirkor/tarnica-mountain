package pl.com.bernas.tarnica.user.model;

import java.util.Set;

import pl.com.bernas.tarnica.model.TarnicaEntity;

public interface User extends TarnicaEntity {
	public String getUsername();
		
	public String getPassword();

	public boolean isOnline();

	public UserDetails getDetails();

	public UserAddress getAddress();

	public Set<Role> getRoles();
}
