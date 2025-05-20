package com.poon20251.mercado.models;

import com.poon20251.mercado.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    private Double unitPrice;

}


