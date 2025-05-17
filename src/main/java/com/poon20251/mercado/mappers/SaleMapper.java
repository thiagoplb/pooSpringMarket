package com.poon20251.mercado.mappers;

import com.poon20251.mercado.DTO.SaleRequestDTO;
import com.poon20251.mercado.DTO.SaleResponseDTO;
import com.poon20251.mercado.models.SaleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ConsumerMapper.class, ItemSaleMapper.class})
public interface SaleMapper {
    @Mapping(source = "consumerModel", target = "consumerResponseDTO")
    @Mapping(source = "itemsSaleModel", target = "itemsSaleResponseDTO")
    SaleResponseDTO toDTO(SaleModel saleModel);

    @Mapping(source = "cpf", target = "consumerModel.cpf")
    @Mapping(source = "itemsSaleRequestDTO", target = "itemsSaleModel")
    SaleModel toModel(SaleRequestDTO saleRequestDTO);
}
