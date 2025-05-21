package com.poon20251.mercado.repositories;

import com.poon20251.mercado.models.ConsumerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<ConsumerModel, String> {
    List<ConsumerModel> findByNameIgnoreCase(String name);
}
