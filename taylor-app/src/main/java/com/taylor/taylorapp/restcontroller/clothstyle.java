package com.taylor.taylorapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.taylor.taylorapp.repository.Userfetch;
import com.taylor.taylorapp.entities.User;

import java.util.List;


@RestController
@RequestMapping(value = "/contacts/")
public class clothstyle {
    @Autowired
    private Userfetch userrepo;

    @RequestMapping("/login-data/{name}")
    public ResponseEntity login(@PathVariable() String name) {
        System.out.println(name);

        Iterable<User> all = userrepo.findAll();
        User use = userrepo.findByUsername(name);

        System.out.println(all);
        return ResponseEntity.ok().body(use);

    }
    @RequestMapping("/login-dataa/{name}")
    public User loginte(@PathVariable() String name) {
        System.out.println(name);

        Iterable<User> all = userrepo.findAll();
        User use = userrepo.findByUsername(name);

        System.out.println(all);
        return use;

    }
}
