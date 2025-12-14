package com.rh.ferias_api.service;

import com.rh.ferias_api.dto.*;
import com.rh.ferias_api.model.PagamentoFerias;
import com.rh.ferias_api.model.PeriodoFerias;
import com.rh.ferias_api.model.Servidor;
import com.rh.ferias_api.repository.PeriodoFeriasRepository;
import com.rh.ferias_api.repository.ServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeriasService {

    @Autowired
    private PeriodoFeriasRepository periodoFeriasRepository;

    @Autowired
    private ServidorRepository servidorRepository;

    // Busca todos os períodos de férias de um servidor
    public List<PeriodoFeriasResumoDTO> buscarFeriasPorServidor(Long servidorId) {
        if (!servidorRepository.existsById(servidorId)) {
            throw new RuntimeException("Servidor não encontrado com ID: " + servidorId);
        }

        List<PeriodoFerias> periodos = periodoFeriasRepository.findByServidorIdOrderByDataInicioDesc(servidorId);

        return periodos.stream()
                .map(this::converterParaResumoDTO)
                .collect(Collectors.toList());
    }

    // Busca detalhes de um período específico
    public PeriodoFeriasDetalhadoDTO buscarFeriasPorId(Long feriasId) {
        PeriodoFerias periodo = periodoFeriasRepository.findById(feriasId)
                .orElseThrow(() -> new RuntimeException("Período de férias não encontrado com ID: " + feriasId));

        return converterParaDetalhadoDTO(periodo);
    }

    // NOVO: Criar solicitação de férias
    public PeriodoFeriasDetalhadoDTO criarSolicitacaoFerias(PeriodoFeriasRequestDTO request) {
        // Busca o servidor
        Servidor servidor = servidorRepository.findById(request.getServidorId())
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado com ID: " + request.getServidorId()));

        // Valida as datas
        if (request.getDataFim().isBefore(request.getDataInicio())) {
            throw new RuntimeException("Data de fim não pode ser anterior à data de início");
        }

        // Calcula quantidade de dias
        int quantidadeDias = (int) ChronoUnit.DAYS.between(request.getDataInicio(), request.getDataFim()) + 1;

        // Cria o período de férias
        PeriodoFerias periodo = new PeriodoFerias();
        periodo.setServidor(servidor);
        periodo.setDataInicio(request.getDataInicio());
        periodo.setDataFim(request.getDataFim());
        periodo.setQuantidadeDias(quantidadeDias);
        periodo.setAnoReferencia(request.getAnoReferencia());
        periodo.setStatus("PENDENTE");
        periodo.setObservacao(request.getObservacao());

        // Salva no banco
        PeriodoFerias salvo = periodoFeriasRepository.save(periodo);

        return converterParaDetalhadoDTO(salvo);
    }

    // Conversores
    private PeriodoFeriasResumoDTO converterParaResumoDTO(PeriodoFerias periodo) {
        return new PeriodoFeriasResumoDTO(
                periodo.getId(),
                periodo.getDataInicio(),
                periodo.getDataFim(),
                periodo.getQuantidadeDias(),
                periodo.getAnoReferencia(),
                periodo.getStatus()
        );
    }

    private PeriodoFeriasDetalhadoDTO converterParaDetalhadoDTO(PeriodoFerias periodo) {
        PeriodoFeriasDetalhadoDTO dto = new PeriodoFeriasDetalhadoDTO();
        dto.setId(periodo.getId());
        dto.setDataInicio(periodo.getDataInicio());
        dto.setDataFim(periodo.getDataFim());
        dto.setQuantidadeDias(periodo.getQuantidadeDias());
        dto.setAnoReferencia(periodo.getAnoReferencia());
        dto.setStatus(periodo.getStatus());
        dto.setDataSolicitacao(periodo.getDataSolicitacao());
        dto.setObservacao(periodo.getObservacao());

        // Converter servidor
        Servidor servidor = periodo.getServidor();
        dto.setServidor(new ServidorDTO(
                servidor.getId(),
                servidor.getNome(),
                servidor.getMatricula(),
                servidor.getEmail()
        ));

        // Converter pagamento (se existir)
        PagamentoFerias pagamento = periodo.getPagamento();
        if (pagamento != null) {
            dto.setPagamento(new PagamentoFeriasDTO(
                    pagamento.getId(),
                    pagamento.getValorBruto(),
                    pagamento.getValorLiquido(),
                    pagamento.getDataPagamento(),
                    pagamento.getTipoPagamento()
            ));
        }

        return dto;
    }
}