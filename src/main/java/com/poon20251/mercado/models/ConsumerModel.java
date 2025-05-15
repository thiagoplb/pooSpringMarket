package com.poon20251.mercado.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_CONSUMER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")

public class ConsumerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

}
