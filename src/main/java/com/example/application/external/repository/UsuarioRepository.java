package com.example.application.external.repository;

import com.example.application.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLoginAndSenha(String login, String senha);
    boolean existsByLoginAndSenha(String login, String senha);

    Usuario findByLogin(String login);
}
