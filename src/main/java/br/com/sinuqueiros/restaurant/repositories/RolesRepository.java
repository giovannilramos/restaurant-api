package br.com.sinuqueiros.restaurant.repositories;

import br.com.sinuqueiros.restaurant.entities.RolesEntity;
import br.com.sinuqueiros.restaurant.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    RolesEntity findByRoleName(final RoleNameEnum roleName);
}
