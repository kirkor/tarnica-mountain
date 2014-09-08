package pl.com.bernas.ioz.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import pl.com.bernas.tarnica.user.model.UserAddress;

@Embeddable
public class UserAddressEntity implements Serializable, UserAddress {

	private static final long serialVersionUID = 3767313076964628529L;

	@Column(name = "country")
	private String country;
	@Column(name = "province")
	private String province;
	@Column(name = "city")
	private String city;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

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
