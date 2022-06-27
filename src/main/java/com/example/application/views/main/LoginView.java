package com.example.application.views.main;

import com.example.application.domain.service.UsuarioService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;

@PageTitle("Login")
@Route("login")
@PermitAll
public class LoginView extends HorizontalLayout {

    @Autowired
    private UsuarioService usuarioService;

    public LoginView() {
        var login = new LoginForm();
        login.setAction("login");

//        login.addLoginListener(e -> {
//            var isLogado = usuarioService.isExisteUsuario(e.getUsername(), e.getPassword());
//
//            if(!isLogado) {
//                Notification.show("Usuario e/ou senha não encontrados", 1500, Notification.Position.MIDDLE).setThemeName("error");
//                login.setEnabled(true);
//                return;
//            }
//
//            UI.getCurrent().navigate("");
//        });
        add(login);
    }

}
