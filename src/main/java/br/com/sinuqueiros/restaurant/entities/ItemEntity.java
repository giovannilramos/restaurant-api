package br.com.sinuqueiros.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "item_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private BigDecimal price;
}
