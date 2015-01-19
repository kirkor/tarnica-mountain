
package pl.com.bernas.tarnica.security.dozer.factory;
import org.dozer.BeanFactory;

import pl.com.bernas.tarnica.security.domain.AuthenticatedUserRoleImpl;
import pl.com.bernas.tarnica.user.model.Role;

public class AuthenticatedUserRoleFactory implements BeanFactory {

	@Override
	public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
		Role role = (Role) source;
		return new AuthenticatedUserRoleImpl(role.getName());
	}

}
