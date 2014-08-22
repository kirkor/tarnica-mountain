package pl.com.bernas.ioz.user.domain;

import pl.com.bernas.tarnica.dto.AbstractDto;
import pl.com.bernas.tarnica.model.TarnicaEntity;
import pl.com.bernas.tarnica.user.model.Role;

public class RoleDto extends AbstractDto implements Role, TarnicaEntity {

	private static final long serialVersionUID = -8480739994130342811L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
