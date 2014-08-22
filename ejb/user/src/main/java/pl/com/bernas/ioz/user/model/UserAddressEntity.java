package pl.com.bernas.ioz.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import pl.com.bernas.tarnica.user.model.UserAddress;

/**
 * @author octavian.rusu
 * @version 1.0 Date: Feb 21, 2010
 */

@Embeddable
public class UserAddressEntity implements Serializable, UserAddress {

	private static final long serialVersionUID = 3767313076964628529L;

	private String country;
	private String province;
	private String city;

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "UserAddress{" + "country='" + country + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + '}';
	}
}
