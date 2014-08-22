package pl.com.bernas.tarnica.model;

import java.io.Serializable;
import java.sql.Timestamp;

public interface TarnicaEntity extends Serializable {

	public Long getId();

	public Timestamp getCreationDate();
}
