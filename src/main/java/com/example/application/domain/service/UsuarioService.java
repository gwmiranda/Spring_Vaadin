package com.example.application.domain.service;

import com.example.application.external.repository.UsuarioRepository;
import com.example.application.util.Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isExisteUsuario(String login, String senha) {
        return usuarioRepository.existsByLoginAndSenha(login, Crypt.md5(senha));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return Optional.ofNullable(usuarioRepository.findByLogin(login))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }
}
