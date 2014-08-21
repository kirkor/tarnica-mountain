package pl.com.bernas.ioz.user.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import pl.com.bernas.ioz.user.model.UserDetails;

/**
 * @author octavian.rusu
 * @version 1.0 Date: Feb 21, 2010
 */

@Embeddable
public class UserDetailsEntity implements Serializable, UserDetails {

	private static final long serialVersionUID = -2338876410494616094L;

	private String firstName;
	private String lastName;
	private String gender;
	private Date birthDate;

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "birth_date")
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
