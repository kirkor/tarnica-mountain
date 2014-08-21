package pl.com.bernas.ioz.user.dao;

import pl.com.bernas.ioz.dao.GenericDao;
import pl.com.bernas.ioz.user.model.Role;


public interface RoleDao<T extends Role> extends GenericDao<T, Long>{
    T findByName(String roleName);
}
