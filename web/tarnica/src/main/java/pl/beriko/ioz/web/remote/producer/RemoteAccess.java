package pl.beriko.ioz.web.remote.producer;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class RemoteAccess {

	protected Context ctx;
	protected static Log log = LogFactory.getLog(RemoteAccess.class);

	protected RemoteAccess() throws NamingException {
		Properties jndiProps = new Properties();
		jndiProps.put("jboss.naming.client.ejb.context", true);
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		ctx = new InitialContext(jndiProps);
	}
}
