package pl.beriko.ioz.web.controller.security;

import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import pl.beriko.ioz.web.exception.SecurityViolationException;
import pl.beriko.ioz.web.util.SecurityUtil;

import java.io.Serializable;
import java.security.Permission;
import java.util.concurrent.Callable;

/**
 * Project: Suggestion List
 * User: Octavian Rusu
 * Date: 05/27/2010
 */
public abstract class SecurityController implements Serializable {

	private static final long serialVersionUID = 8355511577165829602L;
	
    public String getCheckPermissions() {
        if (getPermisssions() != null && !SecurityUtil.hasPermissions(getPermisssions())) {
            publishException(new SecurityViolationException("You have no rights to access this page."));
        }
        return "";
    }

    protected <T> T execute(Callable<T> c) throws Exception {
        try {
            return c.call();
        } catch (Exception e) {
            publishException(e);
            throw e;
        }
    }

    protected void publishException(Exception e) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExceptionQueuedEventContext eventContext = new ExceptionQueuedEventContext(ctx, e);
        ctx.getApplication().publishEvent(ctx, ExceptionQueuedEvent.class, eventContext);
    }

    protected abstract Permission[] getPermisssions();

}
