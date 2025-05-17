package com.poon20251.mercado.mappers;

import com.poon20251.mercado.DTO.ConsumerRequestDTO;
import com.poon20251.mercado.DTO.ConsumerResponseDTO;
import com.poon20251.mercado.models.ConsumerModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConsumerMapper {
    ConsumerResponseDTO toDTO(ConsumerModel consumerModel);
    ConsumerModel toModel(ConsumerRequestDTO consumerRequestDTO);
}
