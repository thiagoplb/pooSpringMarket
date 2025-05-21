package com.poon20251.mercado.controller;

import com.poon20251.mercado.DTO.SaleRequestDTO;
import com.poon20251.mercado.DTO.SaleResponseDTO;
import com.poon20251.mercado.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> create(@RequestBody SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO saleResponseDTO = saleService.create(saleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleResponseDTO);
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<SaleResponseDTO> findByCpf(@PathVariable Long id) {
        SaleResponseDTO saleResponseDTO = saleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(saleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> findAll() {
        List<SaleResponseDTO> saleResponseDTOs = saleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(saleResponseDTOs);
    }

    @GetMapping("/sales/{cpf}")
    public ResponseEntity<List<SaleResponseDTO>> findByCpf(@PathVariable String cpf) {
        List<SaleResponseDTO> saleResponseDTO = saleService.findByConsumerModel_Cpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(saleResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
