package com.rh.ferias_api.controller;

import com.rh.ferias_api.dto.PeriodoFeriasDetalhadoDTO;
import com.rh.ferias_api.dto.PeriodoFeriasRequestDTO;
import com.rh.ferias_api.dto.PeriodoFeriasResumoDTO;
import com.rh.ferias_api.service.FeriasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeriasController {

    @Autowired
    private FeriasService feriasService;

    /**
     * GET /api/servidores/{id}/ferias
     * Retorna todos os períodos de férias de um servidor
     */
    @GetMapping("/servidores/{id}/ferias")
    public ResponseEntity<List<PeriodoFeriasResumoDTO>> listarFeriasDoServidor(@PathVariable Long id) {
        List<PeriodoFeriasResumoDTO> ferias = feriasService.buscarFeriasPorServidor(id);
        return ResponseEntity.ok(ferias);
    }

    /**
     * GET /api/ferias/{id}
     */
    @GetMapping("/ferias/{id}")
    public ResponseEntity<PeriodoFeriasDetalhadoDTO> buscarDetalheFerias(@PathVariable Long id) {
        PeriodoFeriasDetalhadoDTO ferias = feriasService.buscarFeriasPorId(id);
        return ResponseEntity.ok(ferias);
    }

    /**
     * POST /api/ferias
     * Cria uma nova solicitação de férias
     */
    @PostMapping("/ferias")
    public ResponseEntity<PeriodoFeriasDetalhadoDTO> criarSolicitacaoFerias(
            @Valid @RequestBody PeriodoFeriasRequestDTO request) {
        PeriodoFeriasDetalhadoDTO ferias = feriasService.criarSolicitacaoFerias(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ferias);
    }
}