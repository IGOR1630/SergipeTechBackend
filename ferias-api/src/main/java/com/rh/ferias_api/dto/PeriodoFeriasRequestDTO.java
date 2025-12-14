package com.rh.ferias_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoFeriasRequestDTO {

    @NotNull(message = "ID do servidor é obrigatório")
    private Long servidorId;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    private LocalDate dataFim;

    @NotNull(message = "Ano de referência é obrigatório")
    private Integer anoReferencia;

    private String observacao;
}