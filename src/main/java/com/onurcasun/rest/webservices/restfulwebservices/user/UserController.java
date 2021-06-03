package com.onurcasun.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User ID not found: " + id);
        }

        return user;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("User ID not found: " + id);

        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        // Saving user via user data service.
        User savedUser = service.save(user);

        // callback about saved user to user which calling this method.
        ServletUriComponentsBuilder fromCurrentRequest = ServletUriComponentsBuilder.fromCurrentRequest();
        URI uri = buildUriForUser(savedUser, fromCurrentRequest);
        return createResponseEntityByUri(uri);
    }

    private URI buildUriForUser(User savedUser, ServletUriComponentsBuilder fromCurrentRequest) {
        UriComponentsBuilder uriCompBuilder = fromCurrentRequest.path("/{id}"); // "/users/5"
        UriComponents uriComponent = uriCompBuilder.buildAndExpand(savedUser.getId());
        return uriComponent.toUri();
    }

    private ResponseEntity<Object> createResponseEntityByUri(URI uri) {
        BodyBuilder bodyBuilder = ResponseEntity.created(uri);
        ResponseEntity<Object> responseEntity = bodyBuilder.build();
        return responseEntity;
    }

}
