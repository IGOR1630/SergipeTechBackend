package com.rh.ferias_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoFeriasDetalhadoDTO {
    private Long id;
    private ServidorDTO servidor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer quantidadeDias;
    private Integer anoReferencia;
    private String status;
    private LocalDateTime dataSolicitacao;
    private String observacao;
    private PagamentoFeriasDTO pagamento;
}