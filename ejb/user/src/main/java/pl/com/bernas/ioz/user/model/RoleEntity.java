package pl.com.bernas.ioz.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.com.bernas.tarnica.model.AbstractTarnicaEntity;
import pl.com.bernas.tarnica.model.TarnicaEntity;
import pl.com.bernas.tarnica.user.model.Role;

@Entity
@Table(name = "roles")
public class RoleEntity extends AbstractTarnicaEntity implements Role, TarnicaEntity {

	private static final long serialVersionUID = 2452398568786544541L;

	private String name;

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Role{" + "name='" + name + '\'' + "}";
	}

}
