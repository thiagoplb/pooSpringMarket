package com.poon20251.mercado.controller;

import com.poon20251.mercado.DTO.ItemSaleRequestDTO;
import com.poon20251.mercado.DTO.ItemSaleResponseDTO;
import com.poon20251.mercado.services.ItemSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemSaleController {

    @Autowired
    private ItemSaleService itemSaleService;

    @PostMapping
    public ResponseEntity<ItemSaleResponseDTO> itemSale(@RequestBody ItemSaleRequestDTO itemSaleRequestDTO) {
        ItemSaleResponseDTO itemSaleResponseDTO = itemSaleService.create(itemSaleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemSaleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ItemSaleResponseDTO>> findAll() {
        List<ItemSaleResponseDTO> itemSaleResponseDTO = itemSaleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(itemSaleResponseDTO);
    }

    @GetMapping({"/id"})
      public ResponseEntity<ItemSaleResponseDTO> findById(@PathVariable Long id) {
        ItemSaleResponseDTO itemSaleResponseDTO = itemSaleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(itemSaleResponseDTO);
    }

    @DeleteMapping({"/id"})
    public ResponseEntity<ItemSaleResponseDTO> deleteById(@PathVariable Long id) {
        itemSaleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
