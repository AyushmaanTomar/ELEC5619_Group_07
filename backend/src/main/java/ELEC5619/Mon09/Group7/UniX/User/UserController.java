package ELEC5619.Mon09.Group7.UniX.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update username by ID
    @PutMapping("/{id}")
    public User updateUsername(@PathVariable Long id, @RequestBody String username) {
        return userService.updateUsername(id, username);
    }

    // Update password by ID
    @PutMapping("/{id}")
    public User updatePassword(@PathVariable Long id, @RequestBody String password) {
        return userService.updatePassword(id, password);
    }

    // Update phone by ID
    @PutMapping("/{id}")
    public User updatePhone(@PathVariable Long id, @RequestBody String phone) {
        return userService.updatePassword(id, phone);
    }

    // Delete all users
    @DeleteMapping
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "All users have been deleted successfully.";
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}