package com.rh.ferias_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "periodo_ferias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoFerias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "servidor_id", nullable = false)
    private Servidor servidor;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "quantidade_dias", nullable = false)
    private Integer quantidadeDias;

    @Column(name = "ano_referencia", nullable = false)
    private Integer anoReferencia;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @OneToOne(mappedBy = "periodoFerias", cascade = CascadeType.ALL)
    private PagamentoFerias pagamento;

    @PrePersist
    protected void onCreate() {
        dataSolicitacao = LocalDateTime.now();
    }
}