package br.com.sinuqueiros.restaurant.repositories;

import br.com.sinuqueiros.restaurant.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(final String username);
}
