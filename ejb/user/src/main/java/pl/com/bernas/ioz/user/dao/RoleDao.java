package pl.com.bernas.ioz.user.dao;

import pl.com.bernas.tarnica.dao.GenericDao;
import pl.com.bernas.tarnica.user.model.Role;


public interface RoleDao<T extends Role> extends GenericDao<T, Long>{
    T findByName(String roleName);
}
