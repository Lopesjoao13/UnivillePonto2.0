package com.example.Univille_PontoPonto;

import com.example.Univille_PontoPonto.entity.Departamento;
import com.example.Univille_PontoPonto.entity.Funcionario;
import com.example.Univille_PontoPonto.entity.Ponto;
import com.example.Univille_PontoPonto.repository.DepartamentoRepository;
import com.example.Univille_PontoPonto.repository.FuncionarioRepository;
import com.example.Univille_PontoPonto.repository.PontoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.sql.Date;
import java.time.LocalDateTime;

@SpringBootApplication
public class UnivillePontoPontoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnivillePontoPontoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(DepartamentoRepository deprepo, FuncionarioRepository funrepo, PontoRepository ponrepo){
        return (args) -> {
            var dep = new Departamento();
            dep.setNomeDepartamento("Desenvolvimento");
            deprepo.save(dep);

            var func = new Funcionario();
            func.setMatricula("001");
            func.setNomeFuncionario("Eduardo");
            func.setDepartamentoFuncionario(dep);
            funrepo.save(func);

            var pont = new Ponto();
            String data = "2026-04-08T20:12:00";
            pont.setDataHora(LocalDateTime.parse(data));
            pont.setFuncionarioPonto(func);
            ponrepo.save(pont);
        };
    }

}
