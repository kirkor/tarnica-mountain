package pl.com.bernas.tarnica.user.model;

import java.security.Principal;
import java.util.Set;

import pl.com.bernas.tarnica.model.TarnicaEntity;

public interface Role extends TarnicaEntity {
	public Set<Principal> getPrincipals();	
	public String getName();
}
