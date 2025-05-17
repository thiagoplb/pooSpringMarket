package com.poon20251.mercado.repositories;

import com.poon20251.mercado.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleModel, Long> {
}
