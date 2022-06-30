package com.example.application.views.main;

import com.example.application.domain.security.SecurityService;
import com.example.application.views.all.AdminView;
import com.example.application.views.all.UserView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@PageTitle("Main")
@Route(value = "")
@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
public class MainView extends AppLayout {
    private SecurityService securityService;

    public MainView(@Autowired SecurityService securityService) {
        this.securityService = securityService;

        var toggle = new DrawerToggle();

        var title = new H2("OP's");
        var tabs = getTabs();
        var button = new Button("Logout", e -> securityService.logout());

        button.setIcon(VaadinIcon.EXIT.create());

        addToDrawer(tabs);
        addToNavbar(false, toggle, title, button);
    }

    private Tabs getTabs() {
        var tabs = new Tabs();

        if(isPermitido("ROLE_USER")) {
            tabs.add(createTab(VaadinIcon.USERS, "Tela do usuario", UserView.class));
        }
        if(isPermitido("ROLE_ADMIN")) {
            tabs.add(createTab(VaadinIcon.USER_STAR, "Tela do adm", AdminView.class));
        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon iconeMenu, String nomeMenu, Class<? extends Component> view) {
        var icon = iconeMenu.create();

        var link = new RouterLink();
        link.add(icon, new Span(nomeMenu));
        link.setRoute(view);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    private boolean isPermitido(String role) {
        return securityService.getAuthenticatedUser().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}
