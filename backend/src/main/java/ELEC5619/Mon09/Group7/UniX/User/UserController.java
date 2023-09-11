package ELEC5619.Mon09.Group7.UniX.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // Save user in database
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    // Can get all user that is save in database
    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
