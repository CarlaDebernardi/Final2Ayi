package com.ayi.final2.controller;

import com.ayi.final2.entity.Usuario;
import com.ayi.final2.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;
    @PostMapping("/ingresar")
    ResponseEntity<?> ingresar(@RequestParam String nombre, @RequestParam String password) {
        Usuario usuario = usuarioService.encontrarUsuario2(nombre, password);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return new ResponseEntity<>("Autenticación exitosa", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Los datos ingresados no corresponden a un usuario registrado.", HttpStatus.NOT_FOUND);
        }

    }
}
