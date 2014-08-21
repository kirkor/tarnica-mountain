package pl.com.bernas.ioz.security.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

public interface AuthorizedUser extends Serializable, Principal {

	public Long getId();

	public String getUsername();
	
	public String getFirstName();
	
	public String getLastName();

	public boolean isOnline();

	public Set<AuthorizedUserRole> getRoles();

}
