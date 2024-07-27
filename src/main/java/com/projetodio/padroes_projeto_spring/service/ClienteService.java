package com.projetodio.padroes_projeto_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodio.padroes_projeto_spring.model.Cliente;
import com.projetodio.padroes_projeto_spring.model.Endereco;
import com.projetodio.padroes_projeto_spring.repository.ClienteRepository;
import com.projetodio.padroes_projeto_spring.repository.EnderecoRepository;
import com.projetodio.padroes_projeto_spring.service.serviceInterface.ClienteServiceInterface;

/**
 * @author luiz.vieira
 */
@Service
public class ClienteService implements ClienteServiceInterface {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }

}
