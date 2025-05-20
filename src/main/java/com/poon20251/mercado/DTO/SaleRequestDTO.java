package com.poon20251.mercado.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record SaleRequestDTO(
        String cpf,
        List<ItemSaleRequestDTO> itemsSaleRequestDTO
) {
}
