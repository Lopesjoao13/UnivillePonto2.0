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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class UnivillePontoPontoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnivillePontoPontoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(DepartamentoRepository deprepo, FuncionarioRepository funrepo, PontoRepository ponrepo) {
        return (args) -> {
            // 1. Criar e Salvar
            var dep = new Departamento();
            dep.setNomeDepartamento("Desenvolvimento");
            deprepo.save(dep);

            var func = new Funcionario();
            func.setMatricula("001");
            func.setNomeFuncionario("Eduardo");
            func.setDepartamentoFuncionario(dep);
            funrepo.save(func);

            // 2. Registrar Entrada (8:00)
            var entrada = new Ponto();
            entrada.setDataHora(LocalDateTime.parse("2026-04-08T08:00:00"));
            entrada.setFuncionarioPonto(func);
            ponrepo.save(entrada);

            // 3. Registrar Saída (12:00)
            var saida = new Ponto();
            saida.setDataHora(LocalDateTime.parse("2026-04-08T12:00:00"));
            saida.setFuncionarioPonto(func);
            ponrepo.save(saida);

            // 4. Buscar usando o ID gerado pelo banco para o Eduardo
            List<Ponto> pontos = ponrepo.buscarPonto(func.getIdFuncionario(), LocalDateTime.parse("2026-04-08T00:00:00"));

            if (pontos.isEmpty()) {
                System.out.println("Nenhum ponto encontrado.");
            } else {
                StringBuilder linhas = new StringBuilder();
                long totalMinutos = 0;

                for (int i = 0; i < pontos.size(); i += 2) {
                    LocalDateTime ent = pontos.get(i).getDataHora();
                    String strEnt = String.format("%02d:%02d", ent.getHour(), ent.getMinute());
                    String strSai = "--:--";
                    String strDur = "00:00";

                    if (i + 1 < pontos.size()) {
                        LocalDateTime sai = pontos.get(i + 1).getDataHora();
                        strSai = String.format("%02d:%02d", sai.getHour(), sai.getMinute());

                        Duration d = Duration.between(ent, sai);
                        totalMinutos += d.toMinutes();
                        strDur = String.format("%02d:%02d", d.toMinutes() / 60, d.toMinutes() % 60);
                    }
                    linhas.append(String.format("%-9s %-7s %s\n", strEnt, strSai, strDur));
                }

                // O PRINT FORMATADO QUE VOCÊ PEDIU:
                System.out.printf("""
                    
                    RELATÓRIO DE PONTO
                    Funcionário  : %s
                    Departamento : %s
                    Data         : %s
                    
                    Entrada   Saída   Horas
                    ----------------------------
                    %s----------------------------
                    Total trabalhado : %02d:%02d
                    
                    """,
                        func.getNomeFuncionario(),
                        dep.getNomeDepartamento(),
                        pontos.get(0).getDataHora().toLocalDate(),
                        linhas.toString(),
                        totalMinutos / 60,
                        totalMinutos % 60);
            }
        };
    }
}