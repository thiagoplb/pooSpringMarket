package com.poon20251.mercado.DTO;

import com.poon20251.mercado.enums.ProductType;

public record ProductResponseDTO(
        Long id,
        String name,
        String brand,
        Integer stockQuantity,
        ProductType productType,
        Double unitPrice
) {
}
