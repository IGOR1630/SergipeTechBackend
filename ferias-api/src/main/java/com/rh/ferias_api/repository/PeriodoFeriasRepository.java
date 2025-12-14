package com.rh.ferias_api.repository;

import com.rh.ferias_api.model.PeriodoFerias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoFeriasRepository extends JpaRepository<PeriodoFerias, Long> {
    List<PeriodoFerias> findByServidorIdOrderByDataInicioDesc(Long servidorId);
}