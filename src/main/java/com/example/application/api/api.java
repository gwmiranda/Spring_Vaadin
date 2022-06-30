package com.example.application.api;

import com.example.application.domain.entity.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class api {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Usuario> index() {
        var user = new Usuario(1L, "admin", "123", "admin");
        var user1 = new Usuario(2L, "user", "456", "user");
        var user2 = new Usuario(3L, "cas", "789", "admin, user");
        var user3 = new Usuario(4L
            , "teste", "147", "admin");
        return List.of(user, user1, user2, user3);
    }
}
