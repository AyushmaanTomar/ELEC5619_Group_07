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
            case "invalid_password":
                return new ResponseEntity<>("Password does not meet the requirements", HttpStatus.BAD_REQUEST);  // HTTP 400
            case "invalid_email":
                return new ResponseEntity<>("Email is not a valid university email", HttpStatus.BAD_REQUEST);  // HTTP 400
            case "email_exists":
                return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);  // HTTP 400
            default:
                return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }


    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        String result = userService.updateUser(user);
        switch(result) {
            case "updated":
                return new ResponseEntity<>("User updated successfully", HttpStatus.OK);  // HTTP 200
            case "student_not_found":
                return new ResponseEntity<>("Student does not exist in the database", HttpStatus.NOT_FOUND);  // HTTP 404
            default:
                return new ResponseEntity<>("Failed to update user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }


    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        String result = userService.deleteUser(user);
        switch(result) {
            case "deleted":
                return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);  // HTTP 204
            case "student_not_found":
                return new ResponseEntity<>("Student does not exist", HttpStatus.NOT_FOUND);  // HTTP 404
            default:
                return new ResponseEntity<>("Failed to delete user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String result = userService.authenticateUser(user);
        switch(result) {
            case "Authenticated successfully":
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
            case "invalid_password":
                return new ResponseEntity<>("Password does not meet the requirements", HttpStatus.BAD_REQUEST);  // HTTP 400
            case "update_failure":
            default:
                return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @PutMapping("/{userId}/profileImage")
    public ResponseEntity<String> setUserProfileImage(@PathVariable Integer userId, @RequestParam String imagePath) {
        String result = userService.setUserProfileImage(userId, imagePath);
        switch (result) {
            case "Profile image updated successfully.":
                return new ResponseEntity<>("Profile image updated successfully", HttpStatus.OK); // HTTP 200
            case "User not found.":
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND); // HTTP 404
            default:
                return new ResponseEntity<>("Failed to set profile image", HttpStatus.BAD_REQUEST); // HTTP 400
        }
    }

    @GetMapping("/{userId}/profileImage")
    public ResponseEntity<String> getUserProfileImage(@PathVariable Integer userId) {
        String result = userService.getUserProfileImage(userId);
        if (!"User not found.".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.OK); // HTTP 200 (returning the image path)
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND); // HTTP 404
        }
    }

}
