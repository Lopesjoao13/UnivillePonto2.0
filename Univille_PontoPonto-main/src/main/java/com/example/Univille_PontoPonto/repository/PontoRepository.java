package com.example.Univille_PontoPonto.repository;

import com.example.Univille_PontoPonto.entity.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {

    @Query("SELECT p FROM Ponto p " +
            "JOIN p.funcionarioPonto f " +
            "JOIN f.departamentoFuncionario d " +
            "WHERE f.idFuncionario = :idFuncionario " +
            "AND CAST(p.dataHora AS date) = CAST(:dataPonto AS date) " +
            "ORDER BY p.dataHora")
    List<Ponto> buscarPonto(
            @Param("idFuncionario") Long idFuncionario,
            @Param("dataPonto") LocalDateTime dataPonto
    );
}