package pl.com.bernas.tarnica.security.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import pl.com.bernas.tarnica.user.model.Role;

public class RoleFactory {

	public static final String CAN_DELETE_BET = "CAN_DELETE_BET";
	public static final String CAN_MODERATE_BET = "CAN_MODERATE_BET";
	public static final String CAN_ADD_BET = "CAN_ADD_BET";

	public final static String USER = "USER";
	public final static String MODERATOR = "MODERATOR";
	public final static String ADMINISTRATOR = "ADMINISTRATOR";

	public static Set<Role> getFakeRoles() {
		Set<Role> roles = new HashSet<>();
		roles.add(new RoleEntityFakeImpl(1l, new Timestamp(System.currentTimeMillis()), USER).addPrincipal(CAN_ADD_BET));
		roles.add(new RoleEntityFakeImpl(2l, new Timestamp(System.currentTimeMillis()), MODERATOR).addPrincipal(CAN_ADD_BET).addPrincipal(CAN_MODERATE_BET));
		roles.add(new RoleEntityFakeImpl(3l, new Timestamp(System.currentTimeMillis()), ADMINISTRATOR).addPrincipal(CAN_ADD_BET).addPrincipal(CAN_MODERATE_BET)
				.addPrincipal(CAN_DELETE_BET));

		return Collections.unmodifiableSet(roles);
	}
}
