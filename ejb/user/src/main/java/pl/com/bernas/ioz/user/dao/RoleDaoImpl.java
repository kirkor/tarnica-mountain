package pl.com.bernas.ioz.user.dao;

import javax.ejb.Stateless;

import pl.com.bernas.ioz.user.model.RoleEntity;
import pl.com.bernas.tarnica.dao.AbstractGenericDao;
import pl.com.bernas.tarnica.dao.QueryParam;

@Stateless(name = "roleDao")
public class RoleDaoImpl extends AbstractGenericDao<RoleEntity, Long> implements RoleDao<RoleEntity> {

	public RoleDaoImpl() {
		super(RoleEntity.class);
	}

	public RoleEntity findByName(String roleName) {
		String query = "from RoleEntity where name = :roleName";
		return executeQueryWithUniqueResult(query, new QueryParam<String>("roleName", roleName));
	}
}
