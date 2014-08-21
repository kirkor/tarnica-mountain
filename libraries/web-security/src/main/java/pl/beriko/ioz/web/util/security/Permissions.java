package pl.beriko.ioz.web.util.security;

import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: Suggestion List
 * User: Octavian Rusu
 * Date: 05/24/2010
 */
public class Permissions {

    public static final java.security.Permission VIEW_USERS_LIST = new AuthorizationPermission("USER_LIST", "read", "View users list");
    public static final java.security.Permission VIEW_USER = new AuthorizationPermission("USER", "read", "View user profile");
    public static final java.security.Permission CREATE_USER = new AuthorizationPermission("USER", "create", "Create a new user");
    public static final java.security.Permission UPDATE_USER = new AuthorizationPermission("USER", "update", "Update a user");
    public static final java.security.Permission DELETE_USER = new AuthorizationPermission("USER", "delete", "Delete a user");

    public static final java.security.Permission ACCESS_MY_ACCOUNT = new AuthorizationPermission("MY_ACCOUNT", "read", "Access my account");
    public static final java.security.Permission UPDATE_MY_ACCOUNT = new AuthorizationPermission("MY_ACCOUNT", "update", "Update my account");

    private static Map<PermissionType, Permission> permissionMap = new HashMap<PermissionType, Permission>();
    static {
        permissionMap.put(PermissionType.VIEW_USERS_LIST, VIEW_USERS_LIST);
        permissionMap.put(PermissionType.VIEW_USER, VIEW_USER);
        permissionMap.put(PermissionType.CREATE_USER, CREATE_USER);
        permissionMap.put(PermissionType.UPDATE_USER, UPDATE_USER);
        permissionMap.put(PermissionType.DELETE_USER, DELETE_USER);
        permissionMap.put(PermissionType.ACCESS_MY_ACCOUNT, ACCESS_MY_ACCOUNT);
        permissionMap.put(PermissionType.UPDATE_MY_ACCOUNT, UPDATE_MY_ACCOUNT);
    }
    private Permissions() {
    }

    public static Permission[] getPermissionsByTypes(PermissionType... types){
        if(types == null || types.length == 0){
            return null;
        }else{
            Permission[] permissions = new Permission[types.length];
            for(int i=0; i<permissions.length; i++){
                permissions[i] = permissionMap.get(types[i]);
            }
            return permissions;
        }
    }

//    public static final java.security.Permission manageRolesReadPermission = new AuthorizationPermission("manage_roles", "read");
//    public static final java.security.Permission manageRolesCreatePermission = new AuthorizationPermission("manage_roles", "create");
//    public static final java.security.Permission manageRolesUpdatePermission = new AuthorizationPermission("manage_roles", "update");
//    public static final java.security.Permission manageRolesDeletePermission = new AuthorizationPermission("manage_roles", "delete");
//
//    public static final java.security.Permission managePermsReadPermission = new AuthorizationPermission("manage_perms", "read");
//    public static final java.security.Permission managePermsCreatePermission = new AuthorizationPermission("manage_perms", "create");
//    public static final java.security.Permission managePermsUpdatePermission = new AuthorizationPermission("manage_perms", "update");
//    public static final java.security.Permission managePermsDeletePermission = new AuthorizationPermission("manage_perms", "delete");
//
//    public static final java.security.Permission readMyRolesPermission = new AuthorizationPermission("manage_my_roles", "read");
//    public static final java.security.Permission updateMyRolesPermission = new AuthorizationPermission("manage_my_roles", "update");

}
