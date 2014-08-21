BEGIN;

DELETE FROM ioz.users_to_roles;
DELETE FROM ioz.permissions_to_actions;
DELETE FROM ioz.roles_to_permissions;

DELETE FROM ioz.permissions_actions;
DELETE FROM ioz.permissions;
DELETE FROM ioz.roles;
DELETE FROM ioz.users;

COMMIT;