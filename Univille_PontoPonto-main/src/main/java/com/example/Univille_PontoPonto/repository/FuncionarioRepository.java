package com.example.Univille_PontoPonto.repository;

import com.example.Univille_PontoPonto.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByIdFuncionario(Long id);
}
