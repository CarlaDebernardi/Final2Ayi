package com.ayi.final2.service.implementation;

import com.ayi.final2.entity.Usuario;
import com.ayi.final2.repository.IUsuarioRepository;
import com.ayi.final2.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario encontrarUsuario2(String nombre, String password) {
        Usuario user = usuarioRepository.findByNombreUsuario(nombre);
        return user;
    }
}
