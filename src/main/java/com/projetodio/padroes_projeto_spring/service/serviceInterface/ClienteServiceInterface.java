package com.projetodio.padroes_projeto_spring.service.serviceInterface;

import com.projetodio.padroes_projeto_spring.model.Cliente;

public interface ClienteServiceInterface {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}
