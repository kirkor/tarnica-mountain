package pl.com.bernas.ioz.user.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import pl.com.bernas.ioz.user.model.UserEntity;
import pl.com.bernas.tarnica.dao.AbstractGenericDao;
import pl.com.bernas.tarnica.dao.QueryParam;

@Stateless(name = "userDao")
public class UserDaoImpl extends AbstractGenericDao<UserEntity, Long> implements UserDao<UserEntity> {

	public UserDaoImpl() {
		super(UserEntity.class);
	}

	public UserEntity findByUserName(String username) {
		String query = "from UserEntity u where u.username = :username";
		return executeQueryWithUniqueResult(query, new QueryParam<String>("username", username));
	}

	public void setPrimaryEm(EntityManager primaryEm) {
		this.primaryEm = primaryEm;
	}

}
