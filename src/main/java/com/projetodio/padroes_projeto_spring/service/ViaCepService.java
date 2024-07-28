package com.projetodio.padroes_projeto_spring.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetodio.padroes_projeto_spring.model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    Optional<Endereco> consultarCep(@PathVariable("cep") String cep);
}
