package com.rh.ferias_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoFeriasDTO {
    private Long id;
    private BigDecimal valorBruto;
    private BigDecimal valorLiquido;
    private LocalDate dataPagamento;
    private String tipoPagamento;
}