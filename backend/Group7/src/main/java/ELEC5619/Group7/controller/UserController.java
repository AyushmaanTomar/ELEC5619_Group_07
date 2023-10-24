package ELEC5619.Group7.controller;

import ELEC5619.Group7.controller.POJO.LoginRequest;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The application is up...";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @RequestMapping(value = "createsUser", method = RequestMethod.POST)
    public String createUser(@RequestBody User user){
        return userService.createStudent(user);
    }

    @RequestMapping(value = "readsUser", method = RequestMethod.GET)
    public List<User> readUser(){
        return userService.readUser();
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.PUT)
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestBody User user){
        return userService.deleteUser(user);
    }
}
