
package pl.com.bernas.tarnica.security.dozer.factory;
import java.security.Principal;

import org.dozer.BeanFactory;

import pl.com.bernas.tarnica.security.domain.AuthenticatedUserPrincipalImpl;

public class AuthenticatedUserPrincipalFactory implements BeanFactory {

	@Override
	public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
		Principal principal = (Principal) source;
		return new AuthenticatedUserPrincipalImpl(principal.getName());
	}

}
