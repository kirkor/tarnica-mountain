package pl.beriko.ioz.web.util.security;

import java.io.Serializable;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Project: Suggestion List User: Octavian Rusu Date: 05/24/2010
 */
public class AuthorizationPermission extends Permission {

	private static final long serialVersionUID = -7608045103870825874L;
	
	private final static int READ = 0x1;
	private final static String READ_ACTION = "read";
	private final static int CREATE = 0x2;
	private final static String CREATE_ACTION = "create";
	private final static int UPDATE = 0x4;
	private final static String UPDATE_ACTION = "update";
	private final static int DELETE = 0x8;
	private final static String DELETE_ACTION = "delete";
	private final static int ALL = READ | CREATE | UPDATE | DELETE;
	private final static String ALL_ACTION = "read,create,update,delete";
	private final static int NONE = 0x0;
	public static AuthorizationPermission ALL_AUTHORIZATIONS = new AuthorizationPermission("*", ALL, "All permissions");
	private String description;
	private transient int mask;

	public AuthorizationPermission(String name, String action, String description) {
		super("ALL".equalsIgnoreCase(name) ? "*" : name);
		this.description = description;
		init(getMask(action));
	}

	public AuthorizationPermission(String name, int mask, String description) {
		super("ALL".equalsIgnoreCase(name) ? "*" : name);
		this.description = description;
		init(mask);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private void init(int mask) {
		if ((mask & ALL) != mask) {
			throw new IllegalArgumentException("Invalid actions");
		}
		this.mask = mask;
	}

	public boolean implies(Permission permission) {
		if (!(permission instanceof AuthorizationPermission)) {
			return false;
		}
		AuthorizationPermission that = (AuthorizationPermission) permission;
		return ((this.mask & that.mask) == that.mask) && impliesIgnoreMask(that);
	}

	boolean impliesIgnoreMask(AuthorizationPermission that) {
		return getName().equals("*") || getName().equals(that.getName());
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AuthorizationPermission)) {
			return false;
		}
		AuthorizationPermission that = (AuthorizationPermission) obj;
		return this.mask == that.mask && this.getName().equalsIgnoreCase(that.getName());
	}

	public int hashCode() {
		return this.getName().hashCode();
	}

	public String getActions() {
		StringBuilder sb = new StringBuilder();
		boolean comma = false;
		if ((mask & READ) == READ) {
			comma = true;
			sb.append(READ_ACTION);
		}
		if ((mask & CREATE) == CREATE) {
			if (comma)
				sb.append(',');
			else
				comma = true;
			sb.append(CREATE_ACTION);
		}
		if ((mask & UPDATE) == UPDATE) {
			if (comma)
				sb.append(',');
			else
				comma = true;
			sb.append(UPDATE_ACTION);
		}
		if ((mask & DELETE) == DELETE) {
			if (comma)
				sb.append(',');
			sb.append(DELETE_ACTION);
		}

		return sb.toString();
	}

	public PermissionCollection newPermissionCollection() {
		return new AuthorizationPermissionCollection();
	}

	int getMask() {
		return mask;
	}

	/**
	 * Convert an action string to an integer actions mask.
	 * 
	 * @param action
	 *            the action string
	 * @return the action mask
	 */
	private int getMask(String action) {

		if (action == null) {
			throw new NullPointerException("action can't be null");
		}
		if (action.equals("")) {
			throw new IllegalArgumentException("action can't be empty");
		}

		int mask = NONE;

		// Check against use of constants (used heavily within the JDK)
		if (action.equals(READ_ACTION)) {
			return READ;
		} else if (action.equals(CREATE_ACTION)) {
			return CREATE;
		} else if (action.equals(UPDATE_ACTION)) {
			return UPDATE;
		} else if (action.equals(DELETE_ACTION)) {
			return DELETE;
		} else if (action.equals(ALL_ACTION) || action.equals("*")) {
			return ALL;
		}

		char[] a = action.toCharArray();

		int i = a.length - 1;
		if (i < 0)
			return mask;

		while (i != -1) {
			char c;

			// skip whitespace
			while ((i != -1) && ((c = a[i]) == ' ' || c == '\r' || c == '\n' || c == '\f' || c == '\t'))
				i--;

			// check for the known strings
			int matchlen;
			if (i >= 3 && (a[i - 3] == 'r' || a[i - 3] == 'R') && (a[i - 2] == 'e' || a[i - 2] == 'E') && (a[i - 1] == 'a' || a[i - 1] == 'A')
					&& (a[i] == 'd' || a[i] == 'D')) {
				matchlen = 4;
				mask |= READ;

			} else if (i >= 5 && (a[i - 5] == 'c' || a[i - 5] == 'C') && (a[i - 4] == 'r' || a[i - 4] == 'R') && (a[i - 3] == 'e' || a[i - 3] == 'E')
					&& (a[i - 2] == 'a' || a[i - 2] == 'A') && (a[i - 1] == 't' || a[i - 1] == 'T') && (a[i] == 'e' || a[i] == 'E')) {
				matchlen = 6;
				mask |= CREATE;

			} else if (i >= 5 && (a[i - 5] == 'u' || a[i - 5] == 'U') && (a[i - 4] == 'p' || a[i - 4] == 'P') && (a[i - 3] == 'd' || a[i - 3] == 'D')
					&& (a[i - 2] == 'a' || a[i - 2] == 'A') && (a[i - 1] == 't' || a[i - 1] == 'T') && (a[i] == 'e' || a[i] == 'E')) {
				matchlen = 6;
				mask |= UPDATE;

			} else if (i >= 5 && (a[i - 5] == 'd' || a[i - 5] == 'D') && (a[i - 4] == 'e' || a[i - 4] == 'E') && (a[i - 3] == 'l' || a[i - 3] == 'L')
					&& (a[i - 2] == 'e' || a[i - 2] == 'E') && (a[i - 1] == 't' || a[i - 1] == 'T') && (a[i] == 'e' || a[i] == 'E')) {
				matchlen = 6;
				mask |= DELETE;

			} else {
				throw new IllegalArgumentException("invalid permission: " + action);
			}

			// make sure we didn't just match the tail of a word
			// like "ackbarfaccept". Also, skip to the comma.
			boolean seencomma = false;
			while (i >= matchlen && !seencomma) {
				switch (a[i - matchlen]) {
				case ',':
					seencomma = true;
					/* FALLTHROUGH */
				case ' ':
				case '\r':
				case '\n':
				case '\f':
				case '\t':
					break;
				default:
					throw new IllegalArgumentException("invalid permission: " + action);
				}
				i--;
			}
			// point i at the location of the comma minus one (or -1).
			i -= matchlen;
		}

		return mask;
	}

	public static void main(String[] args) {
		System.out.println(READ | CREATE);
		System.out.println(READ | UPDATE);
		System.out.println(READ | DELETE);
		System.out.println(CREATE | UPDATE);
		System.out.println(CREATE | DELETE);
		System.out.println(UPDATE | DELETE);
		System.out.println(READ | CREATE | UPDATE);
		System.out.println(READ | CREATE | DELETE);
		System.out.println(CREATE | UPDATE | DELETE);
		System.out.println(READ | CREATE | UPDATE | DELETE);

		int mask = READ | CREATE | UPDATE;
		System.out.println("------------");
		System.out.println(mask);
		System.out.println(mask & ALL);
	}
}

final class AuthorizationPermissionCollection extends PermissionCollection implements Serializable {

	private static final long serialVersionUID = -5559317100317040112L;

	private List<Permission> perms = new ArrayList<Permission>();

	public void add(Permission permission) {
		if (!(permission instanceof AuthorizationPermission)) {
			throw new IllegalArgumentException("invalid permission: " + permission);
		}
		if (isReadOnly()) {
			throw new SecurityException("attempt to add a Permission to a readonly PermissionCollection");
		}
		synchronized (this) {
			perms.add(0, permission);
		}
	}

	public boolean implies(Permission permission) {
		if (!(permission instanceof AuthorizationPermission)) {
			return false;
		}
		AuthorizationPermission np = (AuthorizationPermission) permission;
		int desired = np.getMask();
		int effective = 0;
		int needed = desired;
		synchronized (this) {
			int len = perms.size();
			for (int i = 0; i < len; i++) {
				AuthorizationPermission x = (AuthorizationPermission) perms.get(i);
				if (x.impliesIgnoreMask(np) && (needed & x.getMask()) != 0) {
					effective |= x.getMask();
					if (((effective & desired) == desired) && x.impliesIgnoreMask(np)) {
						return true;
					}
					needed = (desired ^ effective);
				}
			}
		}
		return false;
	}

	public Enumeration<Permission> elements() {
		synchronized (this) {
			return Collections.enumeration(perms);
		}
	}
}
