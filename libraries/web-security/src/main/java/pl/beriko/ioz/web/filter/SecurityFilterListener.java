package pl.beriko.ioz.web.filter;

import java.io.IOException;
import java.security.Permission;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import javax.security.auth.Subject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pl.beriko.ioz.web.constant.WebConstants;

/**
 * @author octavian.rusu
 * @version 1.0 Date: Mar 13, 2010
 */

public class SecurityFilterListener implements Filter {

	protected final Log logger = LogFactory.getLog(getClass());

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException,
			ServletException {
		Subject subject = (Subject) ((HttpServletRequest) servletRequest).getSession().getAttribute(WebConstants.SUBJECT);

		// if (subject == null) {
		// subject = new Subject();
		// ((HttpServletRequest)
		// servletRequest).getSession().setAttribute(WebConstants.SUBJECT,
		// subject);
		// }

		if (subject == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Subject is null continue running with no Subject.");
			}
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (subject.getPrincipals().size() == 0) {
			// TODO: dodac role
			// subject.getPrincipals().add(AppUtil.getAnonymousRole());
		}

		final PrivilegedExceptionAction<Object> continueChain = new PrivilegedExceptionAction<Object>() {
			public Object run() throws IOException, ServletException {
				filterChain.doFilter(servletRequest, servletResponse);
				return null;
			}
		};

		if (logger.isDebugEnabled()) {
			logger.debug("Running as Subject " + subject);
		}
		try {
			Subject.doAs(subject, continueChain);
		} catch (PrivilegedActionException e) {
			throw new ServletException(e.getMessage(), e);
		}
	}

	public static boolean hasPermission(Permission p) {
		SecurityManager sm = System.getSecurityManager();
		try {
			sm.checkPermission(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void destroy() {

	}
}
