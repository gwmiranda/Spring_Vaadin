package com.example.application.views.all;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login")
@Route("login")
@AnonymousAllowed
public class LoginView extends HorizontalLayout {

    private LoginOverlay loginOverlay;

    public LoginView() {
        var login = getLoginOverlay();
        add(login);
    }

    private LoginOverlay getLoginOverlay() {
        LoginI18n i18n = LoginI18n.createDefault();

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Telas de OP's");
        i18nForm.setUsername("Login");
        i18nForm.setPassword("Senha");
        i18nForm.setSubmit("Entrar");
        i18n.setForm(i18nForm);

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Nome de usuário ou senha incorretos.");
        i18nErrorMessage.setMessage("Verifique se seu nome de usuário e senha estão corretos e tente novamente.");
        i18n.setErrorMessage(i18nErrorMessage);

        loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18n);
        loginOverlay.setAction("login");
        loginOverlay.setForgotPasswordButtonVisible(false);
        loginOverlay.setOpened(true);

        verificarURL();

        return loginOverlay;
    }

    private void verificarURL() {
        UI.getCurrent().getPage().fetchCurrentURL(e -> {
          verificarErro(e.getQuery());
        });
    }

    private void verificarErro(String query) {
        if(query != null && query.equals("error")) {
            loginOverlay.setError(true);
        }
    }

}
