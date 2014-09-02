package pl.com.bernas.tarnica.security.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.security.auth.login.LoginException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pl.com.bernas.ioz.security.model.AuthorizedUser;
import pl.com.bernas.ioz.user.service.UserService;
import pl.com.bernas.tarnica.security.domain.mapper.Dozer;
import pl.com.bernas.tarnica.security.domain.mapper.DozerFactory;
import pl.com.bernas.tarnica.user.model.User;

@SuppressWarnings("unused")
public class AuthorizationServiceTest {

	@InjectMocks
	private final AuthorizationServiceImpl authorizationService = new AuthorizationServiceImpl();
	@Mock
	private UserService<User> userService;
	@Spy
	private Dozer dozer = DozerFactory.prepareDoozerForTests();
	@Mock
	private User kirkor;

	private final String userName = "KirkoR";
	private final String userPassword = "123qwe";
	private final String userBadPassword = "123QWE";
	private final String passwordForString123qwe = "150b8d563acdef89a3465e1032997e754a102d90b96b86bb42a79973d6f6987a";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(kirkor.getUsername()).thenReturn("KirkoR");
		when(kirkor.getPassword()).thenReturn(passwordForString123qwe);
		when(userService.findByUserName("KirkoR")).thenReturn(kirkor);

		doAnswer(answer -> when(kirkor.isOnline()).thenReturn(Boolean.TRUE)).when(userService).online(kirkor);
		doAnswer(answer -> when(kirkor.isOnline()).thenReturn(Boolean.FALSE)).when(userService).offline(kirkor);
	}

	@Test
	public void loginIntoExistingAccount() throws LoginException {
		AuthorizedUser login = authorizationService.login(userName, userPassword);

		assertEquals(login.getUsername(), userName);
	}

	@Test(expected = LoginException.class)
	public void loginIntoAlreadyLoggedInAccount() throws LoginException {
		GIVEN: {
			AuthorizedUser login = authorizationService.login(userName, userPassword);
		}

		WHEN: {
			AuthorizedUser login = authorizationService.login(userName, userPassword);
		}

		THEN: {
			// Nothing... exception is thrown before
		}
	}

	@Test(expected = LoginException.class)
	public void loginWithBadPassword() throws LoginException {
		GIVEN: {

		}

		WHEN: {
			AuthorizedUser login = authorizationService.login(userName, userBadPassword);
		}

		THEN: {
			// Nothing... exception is thrown before
		}
	}

	@Test
	public void generateHashedPassword() {
		String password = null;
		GIVEN: {
			password = userPassword;
		}

		String generatedHashForGivenPassword = null;
		WHEN: {
			generatedHashForGivenPassword = authorizationService.generateHashPassword(password);
		}

		THEN: {
			assertEquals(passwordForString123qwe, generatedHashForGivenPassword);
		}
	}

	@Test
	public void testLogout() throws LoginException {
		AuthorizedUser login = null;
		GIVEN: {
			login = authorizationService.login(userName, userPassword);
		}

		WHEN: {
			authorizationService.logout(login);
		}

		THEN: {
			User user = userService.findByUserName("KirkoR");
			assertEquals(Boolean.FALSE, user.isOnline());
		}
	}
}
