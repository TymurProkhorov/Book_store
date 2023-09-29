package mate.academy.bookstore.service;

import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
