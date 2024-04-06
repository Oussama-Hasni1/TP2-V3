package ma.emsi.jpaemsi.repositories;

import ma.emsi.jpaemsi.entities.Role;
import ma.emsi.jpaemsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
