package pl.beriko.ioz.web.util.security;

import java.security.BasicPermission;
import java.security.PermissionCollection;
import java.security.Permission;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author octavian.rusu
 * @version 1.0 Date: Mar 15, 2010
 */

public class URLPermission extends BasicPermission {

	private static final long serialVersionUID = 6105176588682074495L;

	public URLPermission(String name) {
		super(name);
	}

	public PermissionCollection newPermissionCollection() {
		return new URLPermissionCollection();
	}

}

final class URLPermissionCollection extends PermissionCollection implements Serializable {

	private static final long serialVersionUID = -1393866079061981043L;

	private List<Permission> perms = new ArrayList<Permission>();

	public void add(Permission permission) {
		if (!(permission instanceof URLPermission)) {
			throw new IllegalArgumentException("invalid permission: " + permission);
		}
		if (isReadOnly()) {
			throw new SecurityException("attempt to add a Permission to a readonly PermissionCollection");
		}
		synchronized (this) {
			perms.add(permission);
		}
	}

	public boolean implies(Permission permission) {
		if (permission instanceof URLPermission) {
			synchronized (this) {
				return perms.contains(permission);
			}
		} else {
			return false;
		}
	}

	public Enumeration<Permission> elements() {
		synchronized (this) {
			return Collections.enumeration(perms);
		}
	}
}
