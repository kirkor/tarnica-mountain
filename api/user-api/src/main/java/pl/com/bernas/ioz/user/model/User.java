package pl.com.bernas.ioz.user.model;

import java.util.Set;

import pl.com.bernas.ioz.model.IozEntity;

public interface User extends IozEntity {
	public String getUsername();
		
	public String getPassword();

	public boolean isOnline();

	public UserDetails getDetails();

	public UserAddress getAddress();

	public Set<? extends Role> getRoles();
}
