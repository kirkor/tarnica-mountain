package pl.com.bernas.tarnica.security.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.security.auth.login.CredentialNotFoundException;
import javax.security.auth.login.LoginException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.com.bernas.ioz.security.model.AuthenticatedUser;
import pl.com.bernas.ioz.user.service.UserService;
import pl.com.bernas.tarnica.model.mapper.Dozer;
import pl.com.bernas.tarnica.model.mapper.DozerFactory;
import pl.com.bernas.tarnica.user.model.Role;
import pl.com.bernas.tarnica.user.model.User;

public class AuthenticationServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceTest.class);

	@InjectMocks
	private final AuthenticationServiceImpl authorizationService = new AuthenticationServiceImpl();
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
	private static final Set<Role> KIRKOR_ROLES = RoleFactory.getFakeRoles();
	private static final String UNKNOWN_USER = "gbernas";
	private static final String UNKNOWN_USER_PASSWORD = "none";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(kirkor.getRoles()).thenReturn(KIRKOR_ROLES);
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
		AuthenticatedUser authenticatedUser = authorizationService.login(USERNAME, USER_PASSWORD);

		// THEN:
		assertEquals(authenticatedUser.getUsername(), USERNAME);
	}

	@Test
	public void loginByUnknowUserAsAnonymous() throws LoginException {
		// GIVEN:
		
		// WHEN:
		AuthenticatedUser authenticatedUser = authorizationService.login(UNKNOWN_USER, UNKNOWN_USER_PASSWORD);

		// THEN
		assertEquals(AuthenticatedUser.ANONYMOUS, authenticatedUser.getUsername());
	}

	@Test
	public void throwExceptionWhenAlreadyLogedinUserTryToLoginAgain() throws LoginException {
		// GIVEN:
		LoginException expectedException = null;
		authorizationService.login(USERNAME, USER_PASSWORD);

		// WHEN:
		try {
			authorizationService.login(USERNAME, USER_PASSWORD);
		} catch (LoginException ex) {
			LOG.error(ex.getMessage());
			expectedException = ex;
		}

		// THEN:
		assertNotNull(expectedException);
	}

	@Test
	public void throwExceptionWhenUserEnterBadPassword() throws LoginException {
		// GIVEN:
		LoginException expectedException = null;

		// WHEN:
		try {
			authorizationService.login(USERNAME, USER_BADP_ASSWORD);
		} catch (LoginException ex) {
			LOG.error(ex.getMessage());
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
		AuthenticatedUser authenticatedUser = authorizationService.login(USERNAME, USER_PASSWORD);

		// WHEN:
		authorizationService.logout(authenticatedUser);

		// THEN:
		User user = userService.findByUserName(USERNAME);
		assertEquals(Boolean.FALSE, user.isOnline());
	}

	@Test
	public void userSchouldHaveAtLeastOneRole() throws LoginException {
		// GIVEN

		// WHEN:
		AuthenticatedUser authenticatedUser = authorizationService.login(USERNAME, USER_PASSWORD);
		AuthenticatedUser anonymouslyAuthenticatedUser = authorizationService.login(UNKNOWN_USER, UNKNOWN_USER_PASSWORD);

		// THEN
		assertTrue(authenticatedUser.getRoles().size() > 0);
		assertTrue(anonymouslyAuthenticatedUser.getRoles().size() > 0);
	}

	@Test
	public void throwExceptionWhenUserHasNoRole() throws LoginException {
		// GIVEN
		when(kirkor.getRoles()).thenReturn(null);

		// WHEN:
		CredentialNotFoundException credentialNotFoundExceptionForAuthorizedUser = null;
		try {
			authorizationService.login(USERNAME, USER_PASSWORD);
		} catch (CredentialNotFoundException ex) {
			credentialNotFoundExceptionForAuthorizedUser = ex;
		}
		
		CredentialNotFoundException credentialNotFoundExceptionForAnonymouslyAuthorizedUser = null;
		try{
			authorizationService.login(UNKNOWN_USER, UNKNOWN_USER_PASSWORD);
		} catch (CredentialNotFoundException ex) {
			credentialNotFoundExceptionForAnonymouslyAuthorizedUser = ex;
		}

		// THEN
		assertNotNull(credentialNotFoundExceptionForAuthorizedUser);
		assertNull(credentialNotFoundExceptionForAnonymouslyAuthorizedUser); // when anonymous implementation will change, error occurs
	}

	@Test
	public void userSchouldHaveAtLeastOnePrincipalPerRole() {

	}

	@Test
	public void throwExceptionWhenUserHasNoPrincipalInAnyRole() {

	}
}
