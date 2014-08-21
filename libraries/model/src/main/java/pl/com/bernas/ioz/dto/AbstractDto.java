package pl.com.bernas.ioz.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import pl.com.bernas.ioz.model.IozEntity;

public abstract class AbstractDto implements Serializable, IozEntity {

	private static final long serialVersionUID = -675746409256957073L;

	private Long id;
	private Timestamp creationDate;

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
