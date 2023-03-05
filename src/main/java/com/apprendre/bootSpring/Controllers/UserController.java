package com.apprendre.bootSpring.Controllers;


import com.apprendre.bootSpring.Models.UserModel;
import com.apprendre.bootSpring.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // pour le methode GET

    @GetMapping
    public List<UserModel> getUser() {
        return userService.getUser();
    }

    @GetMapping(path = "{id}")
    public UserModel getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * Path variable = variable dans l'url ex: /user/1
     */


    @PostMapping
    public void addUser(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel) {
        userService.updateUser(id, userModel);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
