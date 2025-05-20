package com.poon20251.mercado.services;

import com.poon20251.mercado.DTO.ItemSaleRequestDTO;
import com.poon20251.mercado.DTO.ItemSaleResponseDTO;
import com.poon20251.mercado.mappers.ItemSaleMapper;
import com.poon20251.mercado.models.ItemSaleModel;
import com.poon20251.mercado.models.SaleModel;
import com.poon20251.mercado.repositories.ItemSaleRepository;
import com.poon20251.mercado.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemSaleService {

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleService saleService;

    @Autowired
    private ItemSaleMapper itemSaleMapper;

    /*public ItemSaleResponseDTO create(ItemSaleRequestDTO itemSaleRequestDTO) {
        ItemSaleModel itemSaleModel = itemSaleMapper.toModel(itemSaleRequestDTO);
        itemSaleModel = itemSaleRepository.save(itemSaleModel);
        return itemSaleMapper.toDTO(itemSaleModel);
    }*/

    @Transactional
    public ItemSaleResponseDTO create(ItemSaleRequestDTO itemSaleRequestDTO) {
        ItemSaleModel itemSaleModel = itemSaleMapper.toModel(itemSaleRequestDTO);

        SaleModel sale = saleRepository.findById(itemSaleRequestDTO.saleId())
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + itemSaleRequestDTO.saleId()));
        itemSaleModel.setSale(sale);

        itemSaleModel = itemSaleRepository.save(itemSaleModel);

        // Recalcular e atualizar o total da venda associada
        saleService.recalculateTotalAmount(sale);

        return itemSaleMapper.toDTO(itemSaleModel);
    }

    public ItemSaleResponseDTO findById(Long id) {
        ItemSaleModel itemSaleModel = itemSaleRepository.findById(id).
                orElseThrow(() -> new ItemSaleNotFoundException(id));
        return itemSaleMapper.toDTO(itemSaleModel);
    }

    public List<ItemSaleResponseDTO> findAll() {
        return itemSaleRepository.findAll()
                .stream()
                .map(itemSaleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        ItemSaleModel itemSaleModel = itemSaleRepository.findById(id)
                        .orElseThrow(() -> new ItemSaleNotFoundException(id));
        itemSaleRepository.delete(itemSaleModel);
    }


    public static class ItemSaleNotFoundException extends RuntimeException {
        public ItemSaleNotFoundException(Long id) {
            super("Item with id " + id + " not found");
        }
    }
}
