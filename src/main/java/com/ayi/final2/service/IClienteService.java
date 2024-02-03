package com.ayi.final2.service;

import com.ayi.final2.entity.Cliente;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClienteService {
    @Transactional(readOnly = true)
    List<Cliente> listarClientes();

    @Transactional
    void eliminar(Integer id);

    @Transactional
    Cliente crearCliente(Cliente cliente);

    @Transactional
    Cliente encontrarCliente(Integer id);

    @Transactional
    Cliente modificarCliente(Integer id, Cliente cliente);
}
