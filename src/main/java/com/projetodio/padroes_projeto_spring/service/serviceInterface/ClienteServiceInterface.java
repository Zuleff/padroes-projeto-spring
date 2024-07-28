package com.projetodio.padroes_projeto_spring.service.serviceinterface;

import com.projetodio.padroes_projeto_spring.model.Cliente;
/**
 * @author luiz.vieira
 */
public interface ClienteServiceInterface {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente) throws Exception;

    void deletar(Long id);

}
