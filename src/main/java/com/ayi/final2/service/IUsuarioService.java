package com.ayi.final2.service;

import com.ayi.final2.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

public interface IUsuarioService {
    @Transactional
    Usuario encontrarUsuario2(String nombre, String password);
}
