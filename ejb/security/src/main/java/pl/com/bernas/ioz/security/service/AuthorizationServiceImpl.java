package pl.com.bernas.ioz.security.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.security.auth.login.LoginException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pl.com.bernas.ioz.security.domain.AuthorizedUserImpl;
import pl.com.bernas.ioz.security.domain.mapper.Dozer;
import pl.com.bernas.ioz.security.model.AuthorizedUser;
import pl.com.bernas.ioz.security.service.AuthorizationService;
import pl.com.bernas.ioz.user.model.Role;
import pl.com.bernas.ioz.user.model.User;
import pl.com.bernas.ioz.user.service.UserService;

@Stateless(name = "authorizationService")
@Remote(AuthorizationService.class)
public class AuthorizationServiceImpl implements AuthorizationService {

	private final String salt = "8RaCedRa";

	@EJB(beanName = "userService")
	UserService<User> userService;
	
	@EJB
	Dozer dozer;

	private static final Log log = LogFactory.getLog(AuthorizationServiceImpl.class);
	
	public AuthorizedUser login(String userName, String password) throws LoginException {
		User userDto = userService.findByUserName(userName);
		if (userDto == null) {
			log.error("The user with name '" + userName + "' not found.");
		} else {
			password = this.generateHashPassword(password); 
			
			if (!userDto.getPassword().equals(password)) {
				throw new LoginException("The provided password is incorrect.");
			} else if (userDto.isOnline()) {
				throw new LoginException("User is already logged in.");
			} else {
				userService.online(userDto);
				
				System.out.println(((Role)userDto.getRoles().iterator().next()).getName());

				return dozer.getMapper().map(userDto, AuthorizedUserImpl.class);
			}
		}

		return null;
	}

	public void logout(AuthorizedUser user) {
		User userEntity = userService.findById(user.getId());
		userService.offline(userEntity);
	}
	
	String generateHashPassword(String password) {
		return DigestUtils.sha256Hex(password + this.salt + password);
	}

}
