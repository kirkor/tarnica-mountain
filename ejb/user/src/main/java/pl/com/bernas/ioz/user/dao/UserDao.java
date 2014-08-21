package pl.com.bernas.ioz.user.dao;

import pl.com.bernas.ioz.dao.GenericDao;
import pl.com.bernas.ioz.user.model.User;

public interface UserDao<T extends User> extends GenericDao<T, Long> {

	T findByUserName(String userName);

}
