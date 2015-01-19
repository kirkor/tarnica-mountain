package pl.com.bernas.ioz.security.model;

import java.io.Serializable;
import java.util.Set;

public interface AuthenticatedUser extends Serializable {

	public final static String ANONYMOUS = "ANONYMOUS";

	public Long getId();

	public String getUsername();

	public String getFirstName();

	public String getLastName();

	public boolean isOnline();

	public Set<AuthenticatedUserRole> getRoles();

}
