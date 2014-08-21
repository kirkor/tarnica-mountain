package pl.com.bernas.ioz.model;

import java.io.Serializable;
import java.sql.Timestamp;

public interface IozEntity extends Serializable {

	public Long getId();

	public Timestamp getCreationDate();
}
