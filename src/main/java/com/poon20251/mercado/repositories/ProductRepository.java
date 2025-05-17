package com.poon20251.mercado.repositories;

import com.poon20251.mercado.enums.ProductType;
import com.poon20251.mercado.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByNameIgnoreCase(String name);

    List<ProductModel> findByBrandIgnoreCase(String brand);

    List<ProductModel> findByProductType(ProductType productType);
}