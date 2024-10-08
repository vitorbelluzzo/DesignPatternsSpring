package com.vitorbelluzzo.design_patterns_spring.service.cliente;

import com.vitorbelluzzo.design_patterns_spring.model.Cliente;

import java.util.Optional;

public interface ClienteService {
    Iterable<Cliente> buscartodos();
    Optional<Cliente> buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);
}
