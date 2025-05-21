package com.poon20251.mercado.repositories;

import com.poon20251.mercado.DTO.SaleResponseDTO;
import com.poon20251.mercado.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SaleRepository extends JpaRepository<SaleModel, Long> {

    List<SaleModel> findByConsumerModel_Cpf(String cpf);
}
