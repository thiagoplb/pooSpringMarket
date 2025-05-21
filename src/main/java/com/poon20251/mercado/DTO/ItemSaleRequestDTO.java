package com.poon20251.mercado.DTO;

public record ItemSaleRequestDTO(
        Long saleId,
        Long productId,
        Integer itemQuantity,
        Double unitPrice
) {
}
