package com.example.application.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;
    private String authorities;

    public Usuario() {
    }

    public Usuario(String username, String senha) {
        this.login = username;
        this.senha = senha;
    }

    public Usuario(Long id, String login, String senha, String authorities) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", senha='" + senha + '\'' +
            '}';
    }
}
