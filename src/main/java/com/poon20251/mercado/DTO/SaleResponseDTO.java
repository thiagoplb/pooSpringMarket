package com.poon20251.mercado.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        Long id,
        LocalDateTime saleDateTime,
        Double totalAmount,
        ConsumerResponseDTO consumerResponseDTO,
        List<ItemSaleResponseDTO> itemsSaleResponseDTO
) {
}
