package pl.com.bernas.tarnica.security.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.security.auth.login.CredentialNotFoundException;
import javax.security.auth.login.LoginException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;
import pl.com.bernas.ioz.security.service.AuthenticationService;
import pl.com.bernas.ioz.user.service.UserService;
import pl.com.bernas.tarnica.model.mapper.Dozer;
import pl.com.bernas.tarnica.security.domain.AnonymousUser;
import pl.com.bernas.tarnica.security.domain.AuthenticatedUserImpl;
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
@Remote(AuthenticationService.class)
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final String SALT = "8RaCedRa";

	@EJB(beanName = "userService")
	UserService<User> userService;

	@EJB
	Dozer dozer;

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	public AuthenticatedUser login(String username, String password) throws LoginException {
		LOG.info("Entering login(username={}, password={})", username, password != null ? "!null" : "null");

		User userDto = userService.findByUserName(username);
		if (userDto == null) {
			LOG.error("user not found.");
		} else {
			String generatedHash = this.generateHashPassword(password);

			if (!userDto.getPassword().equals(generatedHash)) {
				throw new LoginException("The provided password is incorrect.");
			} else if (userDto.isOnline()) {
				throw new LoginException("AuthenticatedUser is already logged in.");
			} else {
				checkIfTheUserHasAtLeastOneRole(userDto);

				userService.online(userDto);

				AuthenticatedUserImpl authorizedUser = dozer.getMapper().map(userDto, AuthenticatedUserImpl.class);
				LOG.info("Leaving login(): {}", authorizedUser);
				return authorizedUser;
			}
		}

		return new AnonymousUser();
	}

	private void checkIfTheUserHasAtLeastOneRole(User userDto) throws CredentialNotFoundException {
		if (userDto == null) {
			throw new IllegalArgumentException("User can't be null here");
		}

		if (userDto.getRoles() == null || userDto.getRoles().size() < 1) {
			throw new CredentialNotFoundException();
		}
	}

	public void logout(AuthenticatedUser authenticatedUser) {
		User userEntity = userService.findByUserName(authenticatedUser.getUsername());
		userService.offline(userEntity);
	}

	String generateHashPassword(String password) {
		return DigestUtils.sha256Hex(password + AuthenticationServiceImpl.SALT + password);
	}

}
