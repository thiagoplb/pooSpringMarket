package com.poon20251.mercado.controller;

import com.poon20251.mercado.DTO.ConsumerRequestDTO;
import com.poon20251.mercado.DTO.ConsumerResponseDTO;
import com.poon20251.mercado.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping
    public ResponseEntity<ConsumerResponseDTO> create(@RequestBody ConsumerRequestDTO consumerRequestDTO) {
        ConsumerResponseDTO consumerResponseDTO = consumerService.create(consumerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumerResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ConsumerResponseDTO>> findAll() {
        List<ConsumerResponseDTO> consumersResponseDTO = consumerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(consumersResponseDTO);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ConsumerResponseDTO> findByCpf(@PathVariable String cpf) {
        ConsumerResponseDTO consumerResponseDTO = consumerService.findByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(consumerResponseDTO);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ConsumerResponseDTO> update(
            @PathVariable String cpf,
            @RequestBody ConsumerRequestDTO consumerRequestDTO) {
        ConsumerResponseDTO consumerResponseDTO = consumerService.update(cpf, consumerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(consumerResponseDTO);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ConsumerResponseDTO> delete(@PathVariable String cpf) {
        consumerService.delete(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
