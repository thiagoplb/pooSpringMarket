package com.poon20251.mercado.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record ItemSaleResponseDTO(
        Long id,
        Long productId,
        String productName,
        Integer itemQuantity,
        Double unitPrice

) {}

