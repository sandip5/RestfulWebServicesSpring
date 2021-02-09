package com.sandip.spring.restfulwebservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> showUsers() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id- " + id);
        }

        EntityModel<User> resource = new EntityModel<>(user);

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).showUsers());
        resource.add(webMvcLinkBuilder.withRel("all-user"));

        return resource;
    }
//
//    @PostMapping("/users")
//    public User addNewUser() {
//        User user = new User(1004, "Abhay Singh", LocalDate.parse("2000-12-31"));
//        return service.save(user);
//    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location  =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping( path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if(user == null) {
            throw new UserNotFoundException("id- " + id);
        }
    }
}
