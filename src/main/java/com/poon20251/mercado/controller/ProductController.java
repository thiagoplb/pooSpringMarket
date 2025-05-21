package com.poon20251.mercado.controller;

import com.poon20251.mercado.DTO.ProductRequestDTO;
import com.poon20251.mercado.DTO.ProductResponseDTO;
import com.poon20251.mercado.enums.ProductType;
import com.poon20251.mercado.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.create(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

  @GetMapping("/id/{id}")
  public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<ProductResponseDTO>> findByName(@PathVariable String name) {
        List<ProductResponseDTO> productsResponseDTO = productService.findByNameIgnoreCase(name);
        return ResponseEntity.status(HttpStatus.OK).body(productsResponseDTO);
  }

  @GetMapping("/product-type/{productType}")
  public ResponseEntity<List<ProductResponseDTO>> findByProductType(@PathVariable ProductType productType) {
        List<ProductResponseDTO> productsResponseDTO = productService.findByProductType(productType);
        return ResponseEntity.status(HttpStatus.OK).body(productsResponseDTO);
  }


  @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> productsResponseDTO = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productsResponseDTO);
    }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> update(
          @PathVariable Long id,
          @RequestBody ProductRequestDTO productRequestDTO) {
            ProductResponseDTO productResponseDTO = productService.update(id, productRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
