package com.projetodio.padroes_projeto_spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.projetodio.padroes_projeto_spring.model.Endereco;
/**
 * @author luiz.vieira
 */
public interface EnderecoRepository extends CrudRepository<Endereco, String>{
    

}
