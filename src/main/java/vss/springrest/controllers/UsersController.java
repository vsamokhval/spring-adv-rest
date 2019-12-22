package vss.springrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vss.springrest.model.User;
import vss.springrest.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public List<User> getUsers(@RequestParam(required = false) Integer count,
                               @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortOrder) {
        return userService.getAll(count, sortOrder);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public List<User> findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
