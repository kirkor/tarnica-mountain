package pl.beriko.ioz.web.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author octavian.rusu
 * @version 1.0 Date: Feb 21, 2010
 */
public class AppUtil {

	public static void invalidateSession() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {
			HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
			request.getSession().invalidate();
		}
	}

}
