package com.example.application.domain.service;

import com.example.application.external.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = usuarioRepository.findByLogin(login);

        if(user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new User(user.getLogin(), user.getSenha(), user.getAuthorities());
    }
}
