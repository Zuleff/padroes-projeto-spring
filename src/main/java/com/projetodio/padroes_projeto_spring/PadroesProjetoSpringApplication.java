package com.projetodio.padroes_projeto_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * @author luiz.vieira
 */
@EnableFeignClients
@SpringBootApplication
public class PadroesProjetoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PadroesProjetoSpringApplication.class, args);
        
    }

}
