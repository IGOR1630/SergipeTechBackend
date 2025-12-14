package com.rh.ferias_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento_ferias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoFerias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "periodo_ferias_id", nullable = false, unique = true)
    private PeriodoFerias periodoFerias;

    @Column(name = "valor_bruto", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorBruto;

    @Column(name = "valor_liquido", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorLiquido;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "tipo_pagamento", nullable = false, length = 30)
    private String tipoPagamento;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}