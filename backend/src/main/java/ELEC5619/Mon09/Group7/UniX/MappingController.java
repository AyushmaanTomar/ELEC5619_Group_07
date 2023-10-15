package ELEC5619.Mon09.Group7.UniX;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ELEC5619.Mon09.Group7.UniX.User.User;
import ELEC5619.Mon09.Group7.UniX.User.UserDatabaseHelper;

import java.util.*;
import java.net.URI;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Controller
public class MappingController {
    private final SecureRandom randomNumberGenerator = new SecureRandom();
    private final HexFormat hexFormatter = HexFormat.of();

    List<User> users = null;
    UserDatabaseHelper dbHelper = null;

    /**
     * @param userID
     * @param model
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String username_or_email,
            @RequestBody String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username_or_email)
                    || users.get(i).getEmail().equals(username_or_email)) {
                if (users.get(i).getPassword().equals(password)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).location(URI.create("/main")).build();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user.\n");
    }

    /**
     * @param userID
     * @param model
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody String username, @RequestBody String email,
            @RequestBody String password, @RequestBody String re_password, @RequestBody String phone) {

        // Email is not an usyd email
        if (!email.split("@", 2).equals("uni.sydney.edu.au"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Incorrect email");

        // Password doesnt match re-entered password
        if (!password.equals(re_password))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Password doesn't match");

        if (password.length() < 6)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Password is too short");
        else if (password.length() > 20)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Password is too long");

        boolean have_special = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(0);
            if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c))
                have_special = true;
        }

        if (have_special)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error: Password doesn't contain at least one special character");

        for (int i = 0; i < users.size(); i++) {

            // Same username exists
            if (users.get(i).getUsername().equals(username))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Entered an existing username");

            // Same email exists
            if (users.get(i).getEmail().equals(email))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Entered an exisiting email");

        }

        // User user1 = new User(users.size(), username, email, password, phone);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).location(URI.create("/main")).build();
    }

}