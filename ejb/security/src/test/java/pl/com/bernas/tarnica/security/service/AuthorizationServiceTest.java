package pl.com.bernas.tarnica.security.service;

import static org.junit.Assert.*;
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

public class AuthorizationServiceTest {

	@InjectMocks
	private final AuthorizationServiceImpl authorizationService = new AuthorizationServiceImpl();
	@Mock
	private UserService<User> userService;
	@Spy
	private Dozer dozer = DozerFactory.prepareDoozerForTests();
	@Mock
	private User kirkor;

	private static final String USERNAME = "KirkoR";
	private static final String USER_PASSWORD = "123qwe";
	private static final String USER_BADP_ASSWORD = "123_QWE_5";
	private static final String PASSWORD_FOR_STRING_123QWE = "150b8d563acdef89a3465e1032997e754a102d90b96b86bb42a79973d6f6987a";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(kirkor.getUsername()).thenReturn(USERNAME);
		when(kirkor.getPassword()).thenReturn(PASSWORD_FOR_STRING_123QWE);
		when(userService.findByUserName(USERNAME)).thenReturn(kirkor);

		doAnswer(answer -> when(kirkor.isOnline()).thenReturn(Boolean.TRUE)).when(userService).online(kirkor);
		doAnswer(answer -> when(kirkor.isOnline()).thenReturn(Boolean.FALSE)).when(userService).offline(kirkor);
	}

	@Test
	public void loginIntoExistingAccount() throws LoginException {
		// GIVEN:

		// WHEN:
		AuthorizedUser login = authorizationService.login(USERNAME, USER_PASSWORD);

		// THEN:
		assertEquals(login.getUsername(), USERNAME);
	}

	@Test
	public void tryToLoginByUnknownUser() throws LoginException {
		// GIVEN:
		String unknownUser = "gbernas";
		String unknownUserPassword = "none";

		// WHEN:
		AuthorizedUser login = authorizationService.login(unknownUser, unknownUserPassword);

		// THEN
		assertNull(login);
	}

	@Test
	public void loginIntoAlreadyLoggedInAccount() throws LoginException {
		// GIVEN:
		LoginException expectedException = null;
		authorizationService.login(USERNAME, USER_PASSWORD);

		// WHEN:
		try {
			authorizationService.login(USERNAME, USER_PASSWORD);
		} catch (LoginException ex) {
			expectedException = ex;
		}

		// THEN:
		assertNotNull(expectedException);
	}

	@Test
	public void loginWithBadPassword() throws LoginException {
		// GIVEN:
		LoginException expectedException = null;

		// WHEN:
		try {
			authorizationService.login(USERNAME, USER_BADP_ASSWORD);
		} catch (LoginException ex) {
			expectedException = ex;
		}

		// THEN:
		assertNotNull(expectedException);
	}

	@Test
	public void generateHashedPassword() {
		// GIVEN:
		String password = USER_PASSWORD;

		// WHEN:
		String generatedHashForGivenPassword = authorizationService.generateHashPassword(password);

		// THEN:
		assertEquals(PASSWORD_FOR_STRING_123QWE, generatedHashForGivenPassword);
	}

	@Test
	public void testLogout() throws LoginException {
		// GIVEN:
		AuthorizedUser login = authorizationService.login(USERNAME, USER_PASSWORD);

		// WHEN:
		authorizationService.logout(login);

		// THEN:
		User user = userService.findByUserName(USERNAME);
		assertEquals(Boolean.FALSE, user.isOnline());
	}
}
