package com.poon20251.mercado.models;

import com.poon20251.mercado.DTO.ItemSaleResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_SALE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SaleModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime saleDateTime;

    @Column(nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "consumer_cpf", nullable = false)
    private ConsumerModel consumerModel;

    @OneToMany(mappedBy = "saleModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItemSaleModel> itemsSaleModel = new HashSet<>();


}


