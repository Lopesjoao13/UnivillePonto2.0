package com.example.Univille_PontoPonto.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ponto {
    @Id
    @GeneratedValue
    private Long idPonto;
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(name="idFuncionario")
    private Funcionario funcionarioPonto;

    public Ponto(){}

    public Long getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(long idPonto) {
        this.idPonto = idPonto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Funcionario getFuncionarioPonto() {
        return funcionarioPonto;
    }

    public void setFuncionarioPonto(Funcionario funcionarioPonto) {
        this.funcionarioPonto = funcionarioPonto;
    }
}
