package pl.com.bernas.ioz.security.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.com.bernas.ioz.security.domain.mapper.Dozer;
import pl.com.bernas.ioz.user.model.User;
import pl.com.bernas.ioz.user.service.UserService;

@SuppressWarnings("unchecked")
public class AuthorizationServiceTest {

	private final AuthorizationServiceImpl authorizationService = new AuthorizationServiceImpl();
	private final UserService<User> userService = mock(UserService.class);
	private final Dozer dozer = new Dozer();

	@Before
	public void init() {
		authorizationService.userService = userService;
		authorizationService.dozer = dozer;
	}

	@Test
	public void testLogin() {
		// fail("Not yet implemented");
	}

	@SuppressWarnings("unused")
	@Test
	public void generateHashedPassword() {
		String password = null;
		GIVEN: {
			password = "123qwe";
		}

		String generatedHashForGivenPassword = null;
		WHEN: {
			generatedHashForGivenPassword = authorizationService.generateHashPassword(password);
		}

		THEN: {
			assertEquals("150b8d563acdef89a3465e1032997e754a102d90b96b86bb42a79973d6f6987a", generatedHashForGivenPassword);
		}
	}

	@Test
	public void testLogout() {
		// fail("Not yet implemented");
	}

}
