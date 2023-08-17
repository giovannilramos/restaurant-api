package br.com.sinuqueiros.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="table_number", nullable = false, unique = true)
    private Integer tableNumber;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal total;

    @ManyToMany
    @JoinTable(name = "items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemEntity> items;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
