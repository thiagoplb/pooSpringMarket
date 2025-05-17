package com.poon20251.mercado.mappers;

import com.poon20251.mercado.DTO.ItemSaleRequestDTO;
import com.poon20251.mercado.DTO.ItemSaleResponseDTO;
import com.poon20251.mercado.models.ItemSaleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemSaleMapper {
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.id", target = "productId")
    ItemSaleResponseDTO toDTO(ItemSaleModel itemSaleModel);

    @Mapping(source = "saleId", target = "sale.id")
    @Mapping(source= "productId",target = "product.id")
    ItemSaleModel toModel(ItemSaleRequestDTO itemSaleRequestDTO);
}
