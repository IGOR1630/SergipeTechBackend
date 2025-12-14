package com.rh.ferias_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoFeriasResumoDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer quantidadeDias;
    private Integer anoReferencia;
    private String status;
}