package br.com.sinuqueiros.restaurant.repositories;

import br.com.sinuqueiros.restaurant.entities.OrderEntity;
import br.com.sinuqueiros.restaurant.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByTableNumber(final Integer tableNumber);

    @Modifying
    @Query("UPDATE OrderEntity SET status = :status, updatedAt = CURRENT_TIMESTAMP WHERE id = :id")
    void updateStatus(@Param("id") final Long id, @Param("status") final OrderStatusEnum status);
}
