package com.poon20251.mercado.services;

import com.poon20251.mercado.DTO.ConsumerRequestDTO;
import com.poon20251.mercado.DTO.ConsumerResponseDTO;
import com.poon20251.mercado.mappers.ConsumerMapper;
import com.poon20251.mercado.models.ConsumerModel;
import com.poon20251.mercado.repositories.ConsumerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Transactional
    public ConsumerResponseDTO create(ConsumerRequestDTO consumerRequestDTO) {
        ConsumerModel consumerModel = consumerMapper.toModel(consumerRequestDTO);
        consumerModel = consumerRepository.save(consumerModel);
        return consumerMapper.toDTO(consumerModel);
    }

    public ConsumerResponseDTO findByCpf(String cpf) {
        ConsumerModel consumerModel = consumerRepository.findById(cpf)
                .orElseThrow(() -> new ConsumerNotFoundException(cpf));
        return consumerMapper.toDTO(consumerModel);
    }

    public List<ConsumerResponseDTO> findAll() {
        return consumerRepository.findAll()
                .stream()
                .map(consumerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ConsumerResponseDTO> findByNameIgnoreCase(String name) {
        return consumerRepository.findByNameIgnoreCase(name)
                .stream()
                .map(consumerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumerResponseDTO update(String cpf, ConsumerRequestDTO consumerRequestDTO) {
        ConsumerModel consumerModel = consumerRepository.findById(cpf)
                .orElseThrow(() -> new ConsumerNotFoundException(cpf));

        consumerModel.setName(consumerRequestDTO.name());
        consumerModel = consumerRepository.save(consumerModel);
        return consumerMapper.toDTO(consumerModel);
    }

    @Transactional
    public void delete(String cpf) {
        ConsumerModel consumerModel = consumerRepository.findById(cpf)
                .orElseThrow(() -> new ConsumerNotFoundException(cpf));
        consumerRepository.deleteById(cpf);
    }

    public static class ConsumerNotFoundException extends RuntimeException {
        public ConsumerNotFoundException(String cpf) {
            super("Consumer with CPF " + cpf + " not found");
        }
    }
}

