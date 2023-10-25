package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
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
        switch(result) {
            case "success":
                return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);  // HTTP 201
            default:
                return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> readUser() {
        List<User> users = userService.readUser();
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // HTTP 204
        }
        return new ResponseEntity<>(users, HttpStatus.OK);  // HTTP 200
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        String result = userService.updateUser(user);
        switch(result) {
            case "success":
                return new ResponseEntity<>("User updated successfully", HttpStatus.OK);  // HTTP 200
            default:
                return new ResponseEntity<>("Failed to update user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        String result = userService.deleteUser(user);
        switch(result) {
            case "success":
                return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);  // HTTP 204
            default:
                return new ResponseEntity<>("Failed to delete user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String result = userService.authenticateUser(user);
        switch(result) {
            case "success":
                return new ResponseEntity<>("Login successful", HttpStatus.OK);  // HTTP 200
            case "user_not_found":
                return new ResponseEntity<>("Unable to find username", HttpStatus.NOT_FOUND);  // HTTP 404
            case "incorrect_password":
                return new ResponseEntity<>("Incorrect password", HttpStatus.FORBIDDEN);  // HTTP 403
            default:
                return new ResponseEntity<>("Login error", HttpStatus.UNAUTHORIZED);  // HTTP 401
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody User user, @RequestParam String newPassword) {
        String result = userService.changePassword(user, newPassword);
        switch(result) {
            case "success":
                return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);  // HTTP 200
            default:
                return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }
}
