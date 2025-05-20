package com.poon20251.mercado.services;

import com.poon20251.mercado.DTO.ProductRequestDTO;
import com.poon20251.mercado.DTO.ProductResponseDTO;
import com.poon20251.mercado.enums.ProductType;
import com.poon20251.mercado.mappers.ProductMapper;
import com.poon20251.mercado.models.ProductModel;
import com.poon20251.mercado.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        ProductModel productModel = productMapper.toModel(productRequestDTO);
        productModel = productRepository.save(productModel);
        return productMapper.toDTO(productModel);
    }

    public ProductResponseDTO findById(Long id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toDTO(productModel);
    }

    public List<ProductResponseDTO> findByNameIgnoreCase(String name) {
        return productRepository.findByNameIgnoreCase(name)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findByBrandIgnoreCase(String brand) {
        return productRepository.findByBrandIgnoreCase(brand)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findByProductType(ProductType productType) {
        return productRepository.findByProductType(productType)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productModel.setName(productRequestDTO.name());
        productModel.setBrand(productRequestDTO.brand());
        productModel.setStockQuantity(productRequestDTO.stockQuantity());
        productModel.setProductType(productRequestDTO.productType());
        productModel.setUnitPrice(productRequestDTO.unitPrice());

        productModel = productRepository.save(productModel);
        return productMapper.toDTO(productModel);
    }

    @Transactional
    public void delete(Long id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(productModel);
    }

    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(Long id) {
            super("Product with id " + id + " not found");
        }
    }
}