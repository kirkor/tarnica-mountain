package pl.com.bernas.tarnica.user.model;

import java.io.Serializable;
import java.sql.Date;

public interface UserDetails extends Serializable {

	public String getFirstName();

	public String getLastName();

	public String getGender();

	public Date getBirthDate();
}
