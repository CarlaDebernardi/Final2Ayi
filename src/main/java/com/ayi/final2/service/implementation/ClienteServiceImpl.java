package com.ayi.final2.service.implementation;

import com.ayi.final2.entity.Cliente;
import com.ayi.final2.exception.ClienteNotFoundException;
import com.ayi.final2.repository.IClienteRepository;
import com.ayi.final2.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    IClienteRepository clienteRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);
    }


    @Override
    @Transactional
    public  Cliente crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Cliente encontrarCliente(Integer id) {
        try {
            Optional<Cliente> entityOptional = clienteRepository.findById(id);
            return entityOptional.get();
        } catch (ClienteNotFoundException e) {
            throw new ClienteNotFoundException("El id ingresado no correspondo a ningún cliente registrado.");
        }
    }

    @Override
    @Transactional
    public Cliente modificarCliente(Integer id, Cliente cliente){
        try {
            Optional <Cliente> optionalCliente = Optional.ofNullable(clienteRepository.getReferenceById(id));
            Cliente clienteUpdate = optionalCliente.get();
            if(cliente.getNombre() != null){
                clienteUpdate.setNombre(cliente.getNombre());
            }
            if(cliente.getApellido() != null){
                clienteUpdate.setApellido(cliente.getApellido());
            }
            if(cliente.getFechaDeIngreso() != null){
                clienteUpdate.setFechaDeIngreso(cliente.getFechaDeIngreso());
            }
            if(cliente.getDomicilio() != null){
                clienteUpdate.setDomicilio(cliente.getDomicilio());
            }
            if(cliente.getTelefono() != null){
                clienteUpdate.setTelefono(cliente.getTelefono());
            }
            return clienteRepository.save(clienteUpdate);
        } catch (ClienteNotFoundException e){
            throw new ClienteNotFoundException("El legajo ingresado no correspondo a ningún empleado registrado.");
        }
    }


}
