package pl.com.bernas.ioz.user.domain;

import java.io.Serializable;
import java.sql.Date;

import pl.com.bernas.tarnica.user.model.UserDetails;

public class UserDetailsDto implements Serializable, UserDetails {

	private static final long serialVersionUID = -5398307996072338221L;

	private String firstName;
	private String lastName;
	private String gender;
	private Date birthDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "UserDetails{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gender='" + gender + '\'' + ", birthDate=" + birthDate
				+ '}';
	}
}
