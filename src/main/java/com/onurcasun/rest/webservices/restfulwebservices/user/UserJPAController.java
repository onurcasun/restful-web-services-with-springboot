package com.onurcasun.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User ID not found: " + id);
        }

        EntityModel<User> userEntityModel = EntityModel.of(user.get());
        addAllUsersPageLinkTo(userEntityModel); // "all users page"(/users) link added to userEntityModel
        return userEntityModel;
    }

    private void addAllUsersPageLinkTo(EntityModel<User> userEntityModel) {
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userEntityModel.add(linkToUsers.withRel("all-users"));
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        // Saving user via user data service.
        User savedUser = userRepository.save(user);

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
