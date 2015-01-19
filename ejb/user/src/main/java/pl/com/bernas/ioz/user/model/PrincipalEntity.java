package pl.com.bernas.ioz.user.model;

import java.security.Principal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.com.bernas.tarnica.model.AbstractTarnicaEntity;
import pl.com.bernas.tarnica.model.TarnicaEntity;

@Entity
@Table(name = "permissions")
@Cacheable
@Access(AccessType.FIELD)
public class PrincipalEntity extends AbstractTarnicaEntity implements Principal, TarnicaEntity {

	private static final long serialVersionUID = 2452398568786544541L;

	@Column(name = "name", nullable = false)
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).toString();
	}

}
