package pl.beriko.ioz.web.util;

import java.security.AccessController;
import java.security.Permission;

import javax.faces.context.FacesContext;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import pl.beriko.ioz.web.constant.WebConstants;

public class SecurityUtil {

	public static boolean hasPermissions(Permission... permissions) {
		for (Permission permission : permissions) {
			try {
				AccessController.checkPermission(permission);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasPermission(Permission permission) {
		try {
			AccessController.checkPermission(permission);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean hasAtLeastOnePermission(Permission... permissions) {
		for (Permission permission : permissions) {
			if (hasPermission(permission)) {
				return true;
			}
		}
		return false;
	}

	public static Subject getSubject(){
    	FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			Subject sb = (Subject) request.getSession().getAttribute(WebConstants.SUBJECT);
			if(sb == null) {
				return new Subject();
			} else {
				return sb;
			}
		}
		
		return null;
    }
	
	public static void propagateSubject(Subject sb) {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			request.getSession().setAttribute(WebConstants.SUBJECT, sb);			
		}
	}
}
