package pl.beriko.ioz.web.controller.security;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.Subject;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import pl.beriko.ioz.web.callback.security.HttpCallbackHandler;
import pl.beriko.ioz.web.util.AppUtil;
import pl.beriko.ioz.web.util.SecurityUtil;
import pl.beriko.ioz.web.util.security.LoginConfiguration;
import pl.com.bernas.ioz.security.model.AuthenticatedUser;
import pl.com.bernas.ioz.security.service.AuthenticationService;

@Named(value = "authController")
@ViewScoped
public class AuthenticationController implements Serializable {

	private static final long serialVersionUID = 7372703206344479835L;

	@Inject
	private AuthenticationService userService;

	private String username;
	private String password;
	private String redirectView;
	private AuthenticatedUser authenticatedUser;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public AuthenticatedUser getUser() {
		return authenticatedUser;
	}

	public void login(ActionEvent actionEvent) throws LoginException, ServletException {
		//LoginContext loginContext = createLoginContext();
		//loginContext.login();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		request.login(username, password);
		
		Subject subject = SecurityUtil.getSubject();

		FacesMessage msg = null;
		boolean loggedIn = false;

		RequestContext context = RequestContext.getCurrentInstance();

		authenticatedUser = (AuthenticatedUser) subject.getPublicCredentials().iterator().next();
		redirectIfNecessary();

		if (authenticatedUser != null) {
			loggedIn = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
		} else {
			loggedIn = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("loggedIn", loggedIn);
	}

	public String logout() throws LoginException {
		LoginContext loginContext = createLoginContext();
		loginContext.logout();
		AppUtil.invalidateSession();
		this.authenticatedUser = null;
		return "logout_success";
	}

	private LoginContext createLoginContext() throws LoginException {
		Subject subject = SecurityUtil.getSubject();
		Configuration conf = new LoginConfiguration(userService, authenticatedUser);
		return new LoginContext("ioz", subject, new HttpCallbackHandler(username, password), conf);
	}

	private void redirectIfNecessary() {
		if (redirectView != null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(redirectView);
				redirectView = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setRedirect(String value) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getExpressionFactory().createValueExpression(fc.getELContext(), "#{authController.redirectView}", String.class)
				.setValue(fc.getELContext(), value);
	}

	protected void publishException(Exception e) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExceptionQueuedEventContext eventContext = new ExceptionQueuedEventContext(ctx, e);
		ctx.getApplication().publishEvent(ctx, ExceptionQueuedEvent.class, eventContext);
	}
}
