package pl.com.bernas.ioz.user.model;

import java.security.Principal;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import pl.com.bernas.tarnica.model.AbstractTarnicaEntity;
import pl.com.bernas.tarnica.model.TarnicaEntity;
import pl.com.bernas.tarnica.user.model.Role;

@Entity
@Table(name = "roles")
@Cacheable
@Access(AccessType.FIELD)
public class RoleEntity extends AbstractTarnicaEntity implements Role, TarnicaEntity {

	private static final long serialVersionUID = 2452398568786544541L;

	@Column(name = "name", nullable = false)
	private String name;
	@ManyToMany(targetEntity = PrincipalEntity.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "role_to_principal", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "principal_id"))
	@Fetch(value = FetchMode.SUBSELECT)
	// FIX: HHH-1718
	private Set<Principal> principal;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Set<Principal> getPrincipals() {
		return principal;
	}
	
	public void setPrincipals(Set<Principal> principal) {
		this.principal = principal;
	}
	
	@Override
	public String toString() {
		return "Role{" + "name='" + name + '\'' + "}";
	}

}
