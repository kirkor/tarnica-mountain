package pl.com.bernas.ioz.user.dao;

import pl.com.bernas.tarnica.dao.GenericDao;
import pl.com.bernas.tarnica.user.model.User;

public interface UserDao<T extends User> extends GenericDao<T, Long> {

	T findByUserName(String userName);

}
