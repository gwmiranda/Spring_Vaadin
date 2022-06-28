package com.example.application.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.apache.poi.ss.formula.functions.T;

import javax.annotation.security.RolesAllowed;

@PageTitle("Main")
@Route(value = "")
@RolesAllowed({"USER", "ADMIN"})
public class MainView extends AppLayout {

    public MainView() {
        setPrimarySection(Section.DRAWER);
        var toggle = new DrawerToggle();

        var title = new H2("OP's");
        var tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(true, toggle, title);
    }

    private Tabs getTabs() {
        var tabs = new Tabs();
        tabs.add(
            createTab(VaadinIcon.DASHBOARD, "Say Hellow", UserView.class),
            createTab(VaadinIcon.CART, "Cadastrar", AdminView.class)
        );
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
}
