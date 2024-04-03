package br.com.sinuqueiros.restaurant.repositories;

import br.com.sinuqueiros.restaurant.entities.RolesEntity;
import br.com.sinuqueiros.restaurant.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    Optional<RolesEntity> findByRoleName(final RoleNameEnum roleName);
}
