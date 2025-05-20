package com.poon20251.mercado.mappers;

import com.poon20251.mercado.DTO.ProductRequestDTO;
import com.poon20251.mercado.DTO.ProductResponseDTO;
import com.poon20251.mercado.models.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toDTO(ProductModel productModel);
    ProductModel toModel(ProductRequestDTO productRequestDTO);
}
