package pl.com.bernas.ioz.user.domain;

import java.io.Serializable;

import pl.com.bernas.ioz.user.model.UserAddress;

public class UserAddressDto implements Serializable, UserAddress {

	private static final long serialVersionUID = -2342782990579619681L;

	private String country;
	private String province;
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
