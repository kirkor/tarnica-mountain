package pl.com.bernas.tarnica.security.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.security.auth.login.LoginException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.com.bernas.ioz.security.model.AuthorizedUser;
import pl.com.bernas.ioz.security.service.AuthorizationService;
import pl.com.bernas.ioz.user.service.UserService;
import pl.com.bernas.tarnica.security.domain.AuthorizedUserImpl;
import pl.com.bernas.tarnica.security.domain.mapper.Dozer;
import pl.com.bernas.tarnica.user.model.User;

/**
 * EJB does not support constructor injection, so I made fields in
 * package-private scope for tests purpose
 * 
 * 
 * @author kirkor
 *
 */
@Stateless(name = "authorizationService")
@Remote(AuthorizationService.class)
public class AuthorizationServiceImpl implements AuthorizationService {

	private final String salt = "8RaCedRa";

	@EJB(beanName = "userService")
	UserService<User> userService;

	@EJB
	Dozer dozer;

	private static final Logger log = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

	public AuthorizedUser login(String userName, String password) throws LoginException {
		log.info(String.format("Loggin in %s", userName));

		User userDto = userService.findByUserName(userName);
		if (userDto == null) {
			log.error("The user with name '" + userName + "' not found.");
		} else {
			password = this.generateHashPassword(password);

			if (!userDto.getPassword().equals(password)) {
				String msg = "The provided password is incorrect.";
				log.error(msg);
				throw new LoginException(msg);
			} else if (userDto.isOnline()) {
				String msg = "User is already logged in.";
				log.error(msg);
				throw new LoginException(msg);
			} else {
				userService.online(userDto);
				
				AuthorizedUserImpl authorizedUser = dozer.getMapper().map(userDto, AuthorizedUserImpl.class);
				log.info("Authorization completed with success.");
				return authorizedUser;
			}
		}

		return null;
	}

	public void logout(AuthorizedUser user) {
		User userEntity = userService.findByUserName(user.getUsername());
		userService.offline(userEntity);
	}

	String generateHashPassword(String password) {
		return DigestUtils.sha256Hex(password + this.salt + password);
	}

}
