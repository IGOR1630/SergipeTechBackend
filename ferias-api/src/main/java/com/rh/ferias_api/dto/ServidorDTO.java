package com.rh.ferias_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorDTO {
    private Long id;
    private String nome;
    private String matricula;
    private String email;
}