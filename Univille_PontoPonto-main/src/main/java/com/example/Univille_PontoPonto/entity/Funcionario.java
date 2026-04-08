package com.example.Univille_PontoPonto.entity;

import jakarta.persistence.*;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue
    private long idFuncionario;
    private String nomeFuncionario;
    private String matricula;
    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamentoFuncionario;

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Departamento getDepartamentoFuncionario() {
        return departamentoFuncionario;
    }

    public void setDepartamentoFuncionario(Departamento departamentoFuncionario) {
        this.departamentoFuncionario = departamentoFuncionario;
    }

}