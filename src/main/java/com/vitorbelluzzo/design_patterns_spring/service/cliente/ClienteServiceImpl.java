package com.vitorbelluzzo.design_patterns_spring.service.cliente;

import com.vitorbelluzzo.design_patterns_spring.model.Cliente;
import com.vitorbelluzzo.design_patterns_spring.model.Endereco;
import com.vitorbelluzzo.design_patterns_spring.repository.ClienteRepository;
import com.vitorbelluzzo.design_patterns_spring.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;
    @Autowired
    public EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscartodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void inserir(Cliente cliente) {
        SalvarClienteComCep(cliente);
    }



    @Override
    public void atualizar(Long id, Cliente cliente) {
       Optional<Cliente> cliente1 = clienteRepository.findById(id);
        if (cliente1.isPresent()) {
            SalvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


    private void SalvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
