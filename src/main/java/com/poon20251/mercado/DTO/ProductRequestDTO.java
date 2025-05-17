package com.poon20251.mercado.DTO;

import com.poon20251.mercado.enums.ProductType;

public record ProductRequestDTO(
        String name,
        String brand,
        Integer stockQuantity,
        ProductType productType,
        Double unitPrice
) {
}
