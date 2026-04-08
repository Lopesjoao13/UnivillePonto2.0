package com.example.Univille_PontoPonto.repository;

import com.example.Univille_PontoPonto.entity.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long> {
}
