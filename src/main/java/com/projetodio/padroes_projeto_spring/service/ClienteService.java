package com.projetodio.padroes_projeto_spring.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodio.padroes_projeto_spring.model.Cliente;
import com.projetodio.padroes_projeto_spring.model.Endereco;
import com.projetodio.padroes_projeto_spring.repository.ClienteRepository;
import com.projetodio.padroes_projeto_spring.repository.EnderecoRepository;
import com.projetodio.padroes_projeto_spring.service.serviceinterface.ClienteServiceInterface;
import com.projetodio.padroes_projeto_spring.tratamentoexcecoes.TratamentoExcecoes;

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
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        try {
            if (clienteRepository.existsById(cliente.getId())) {
                salvarClienteComCep(cliente);
            } else {
                throw new Exception("O cliente acima não está cadastrado na base!");
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        try {
            // Verificar se o Endereco do Cliente já existe (pelo CEP).
            String cep = cliente.getEndereco().getCep();
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                // Caso não exista, integrar com o ViaCEP e persistir o retorno.
                //Criando um tratamento para caso não encontre o CEP
                Endereco novoEndereco = viaCepService.consultarCep(cep).orElseThrow();

                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });

            cliente.setEndereco(endereco);
            // Inserir Cliente, vinculando o Endereco (novo ou existente).
            clienteRepository.save(cliente);

        } catch (Exception e) {
            throw new TratamentoExcecoes("O CEP é inválido ou está errado");
        }

    }

}
