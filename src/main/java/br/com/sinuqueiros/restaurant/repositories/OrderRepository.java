package br.com.sinuqueiros.restaurant.repositories;

import br.com.sinuqueiros.restaurant.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByTableNumber(final Integer tableNumber);
}
