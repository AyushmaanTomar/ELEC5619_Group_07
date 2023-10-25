package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // This is to have a common prefix for user related endpoints.
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return new ResponseEntity<>("The application is up...", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String result = userService.createStudent(user);
        if(result.equals("success")) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> readUser() {
        return new ResponseEntity<>(userService.readUser(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String result = userService.authenticateUser(user);
        if(result.equals("success")) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody User user, @RequestParam String newPassword) {
        String result = userService.changePassword(user, newPassword);
        if(result.equals("success")) {
            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);
        }
    }
}
