package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.ItemService;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;


    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user == null) return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
        if (user.getPassword() == null || user.getUserName() == null
                || user.getEmail() == null || user.getPhone() == null
        ) {
            return new ResponseEntity<>("Failed to create user (bad input)", HttpStatus.BAD_REQUEST);
        }
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
            case "username_exists":
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);  // HTTP 400
            default:
                return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }


    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getUserName() == null
                || user.getEmail() == null || user.getPhone() == null
        ) {
            return new ResponseEntity<>("Failed to update user", HttpStatus.BAD_REQUEST);
        }
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


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username,
                                            @RequestParam String password) {
        if ( username == null || password == null ) return new ResponseEntity<>("Login error", HttpStatus.UNAUTHORIZED);
        String result = userService.authenticateUser(username, password);
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
    public ResponseEntity<String> changePassword(@RequestParam String email,
                                                 @RequestParam String newPassword) {
        if (email == null) return new ResponseEntity<>("Login error (user is null)", HttpStatus.UNAUTHORIZED);
        if (newPassword == null) return new ResponseEntity<>("Password does not meet the requirements (password is null)", HttpStatus.BAD_REQUEST);
        String result = userService.changePassword(email, newPassword);
        switch(result) {
            case "success":
                return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);  // HTTP 200
            case "invalid_password":
                return new ResponseEntity<>("Password does not meet the requirements", HttpStatus.BAD_REQUEST);  // HTTP 400
            case "update_failure":
                return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @PutMapping("/{userId}/profileImage")
    public ResponseEntity<String> setUserProfileImage(@PathVariable Integer userId,
                                                      @RequestParam String imagePath) {
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

    @PostMapping("/{userID}/item/{itemID}")
    public ResponseEntity<String> modifyItem (@RequestParam String itemDescription,
                                              @RequestParam String productName,
                                              @RequestParam Double price,
                                              @RequestParam Boolean active,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer itemID) {

        Item item = itemService.getItemByID(itemID);
        User user = userService.getUserById(userId);

        if (!item.getUser().getEmail().equals(user.getEmail())) {
            return new ResponseEntity<>("User and Item not match", HttpStatus.FORBIDDEN);
        }

        switch (itemService.updateItem(item, itemDescription, productName, price, active)) {
            case "Update":
                return new ResponseEntity<>("Item Update Successfully", HttpStatus.OK);
            case "item_not_found":
                return new ResponseEntity<>("Item is ", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>("Failed to modify", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Object> checkEmailExists(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");

        if (email == null || email.trim().isEmpty()) {
            return new ResponseEntity<>(Map.of("error", "Email parameter is missing or empty"), HttpStatus.BAD_REQUEST);
        }

        String result = userService.checkEmailExists(email);
        switch (result) {
            case "exists":
                return new ResponseEntity<>(Map.of("exists", true), HttpStatus.OK);  // HTTP 200
            case "not_exists":
                return new ResponseEntity<>(Map.of("exists", false), HttpStatus.OK);  // HTTP 200
            default:
                return new ResponseEntity<>(Map.of("error", "Failed to check email"), HttpStatus.INTERNAL_SERVER_ERROR);  // HTTP 500
        }
    }

}
