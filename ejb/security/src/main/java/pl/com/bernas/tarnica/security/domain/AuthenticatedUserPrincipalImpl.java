package pl.com.bernas.tarnica.security.domain;

import java.security.Principal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AuthenticatedUserPrincipalImpl implements Principal {

	private final String name;

	public AuthenticatedUserPrincipalImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("name", getName()).toString();
	}

}
