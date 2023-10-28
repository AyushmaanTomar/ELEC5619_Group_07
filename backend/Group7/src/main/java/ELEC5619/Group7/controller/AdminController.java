package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.Admin;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.AdminRepository;
import ELEC5619.Group7.repository.UserRepository;
import ELEC5619.Group7.service.AdminService;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(Admin admin) {
        String result = adminService.authenticateAdmin(admin);
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


    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(Admin admin) {
        String result = adminService.createAdmin(admin);
        switch(result) {
            case "success":
                return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);  // HTTP 201
            case "invalid_password":
                return new ResponseEntity<>("Password does not meet the requirements", HttpStatus.BAD_REQUEST);  // HTTP 400
            case "invalid_username":
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);  // HTTP 400
            default:
                return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);  // HTTP 400
        }
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<User>> readUser() {
        List<User> users = userService.readUser();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // HTTP 204
        }
        return ResponseEntity.ok(users);  // HTTP 200
    }


    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        if (userService.deleteUser(user.getName(), user.getPassword())) {
            return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete Fail", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        switch (userService.createStudent(user)) {
            case "Authenticated successfully":
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            case "user_not_found":
                return new ResponseEntity<>("Unable to find username", HttpStatus.NOT_FOUND);
            case "incorrect_password":
                return new ResponseEntity<>("Incorrect password", HttpStatus.FORBIDDEN);
            default:
                return new ResponseEntity<>("Login error", HttpStatus.UNAUTHORIZED);
        }
    }


}
