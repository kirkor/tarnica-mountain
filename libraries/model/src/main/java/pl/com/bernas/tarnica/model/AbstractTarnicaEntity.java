package pl.com.bernas.tarnica.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * User: iru Date: Feb 10, 2010 Time: 3:26:27 PM
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class AbstractTarnicaEntity implements Serializable, TarnicaEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Version
	@Access(AccessType.FIELD)
	private long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
}
