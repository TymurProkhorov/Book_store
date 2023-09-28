package mate.academy.bookstore.repository.user;

import java.util.Optional;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(RoleName roleName);
}
