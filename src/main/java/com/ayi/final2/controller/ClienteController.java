package com.ayi.final2.controller;

import com.ayi.final2.entity.Cliente;
import com.ayi.final2.exception.ClienteNotFoundException;
import com.ayi.final2.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    public IClienteService clienteService;
    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.listarClientes();
        try {
            if (clientes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();}
            return ResponseEntity.ok(clientes);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.encontrarCliente(id));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());}
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente) {
        try {
            return new ResponseEntity(clienteService.crearCliente(cliente), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente que desea ingresar ya se encuentra en la base de datos");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            return new ResponseEntity(clienteService.modificarCliente(id, cliente), HttpStatus.OK);
        } catch (ClienteNotFoundException clienteNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El nro de Id ingresado no corresponde a un cliente registrado.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id) {
        try {
            if (id == null || id.describeConstable().isEmpty()) {
                throw new IllegalArgumentException("El Id no puede estat vacío.");}

            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
