package br.com.sinuqueiros.restaurant.services.order.dto;

import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    @Setter
    private List<ItemDTO> items;
    private Integer tableNumber;
    @Setter
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
