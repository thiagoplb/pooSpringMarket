package com.poon20251.mercado.services;

import com.poon20251.mercado.DTO.SaleRequestDTO;
import com.poon20251.mercado.DTO.SaleResponseDTO;
import com.poon20251.mercado.mappers.SaleMapper;
import com.poon20251.mercado.models.ItemSaleModel;
import com.poon20251.mercado.models.SaleModel;
import com.poon20251.mercado.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleMapper saleMapper;

    @Transactional
    public SaleResponseDTO create(SaleRequestDTO saleRequestDTO) {
        SaleModel saleModel = saleMapper.toModel(saleRequestDTO);

        saleModel.setSaleDateTime(LocalDateTime.now());
        saleModel.setItemsSaleModel(new HashSet<>());
        double totalAmount = 0.0;
        saleModel.setTotalAmount(totalAmount);

        saleModel = saleRepository.save(saleModel);
        return saleMapper.toDTO(saleModel);
    }

    public SaleResponseDTO findById(Long id) {
        SaleModel saleModel = saleRepository.findById(id).
                orElseThrow(() -> new SaleNotFoundException(id));
        return saleMapper.toDTO(saleModel);
    }

    public List<SaleResponseDTO> findByConsumerModel_Cpf(String cpf) {
        List<SaleModel> sales = saleRepository.findByConsumerModel_Cpf(cpf);
        return sales.stream()
                .map(saleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(saleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        SaleModel saleModel = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException(id));
        saleRepository.delete(saleModel);
    }

    @Transactional
    public void recalculateTotalAmount(SaleModel sale) {
        double total = 0.0;
        for (ItemSaleModel item : sale.getItemsSaleModel()) {
            total += item.getItemQuantity() * item.getUnitPrice();
        }
        sale.setTotalAmount(total);
        saleRepository.save(sale);
    }

    public static class SaleNotFoundException extends RuntimeException {
        public SaleNotFoundException(Long id) {
            super("Sale with id " + id + " not found");
        }
    }
}
