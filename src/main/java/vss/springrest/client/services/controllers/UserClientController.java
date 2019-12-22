package vss.springrest.client.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vss.springrest.client.services.UserRestService;
import vss.springrest.model.User;

import java.util.List;

@RestController
@RequestMapping("/client/users")
public class UserClientController {

    @Autowired
    private UserRestService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public List<User> getUsers(@RequestParam(required = false, defaultValue = "50") Integer count,
                               @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortOrder) {
        return userService.getUsers(count, sortOrder);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public List<User> findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }
}
