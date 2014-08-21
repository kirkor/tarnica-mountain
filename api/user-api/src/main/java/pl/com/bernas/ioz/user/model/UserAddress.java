package pl.com.bernas.ioz.user.model;

import java.io.Serializable;

public interface UserAddress extends Serializable {

	public String getCountry();

	public String getProvince();

	public String getCity();
}
