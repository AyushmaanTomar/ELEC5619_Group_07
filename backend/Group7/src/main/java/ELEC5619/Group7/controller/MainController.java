package ELEC5619.Group7.controller;



import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.SecureRandom;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    private static final SecureRandom randomNumberGenerator = new SecureRandom();
    private final HexFormat hexFormatter = HexFormat.of();
    Map<String, String> sessions = new HashMap<>();

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam(value = "user", defaultValue = "") String userName,
            @RequestParam(value = "password", defaultValue = "") String password,
            Model model) {
        List<User> users = userService.readUser();
        User user = null;

        for (User i: users) {
            if (i.getUserName().equals(userName) && i.getPassword().equals(password)) {
                user = i;
            }
        }

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user.\n");
        }


        byte[] sessionTokenBytes = new byte[16];
        randomNumberGenerator.nextBytes(sessionTokenBytes);
        String sessionToken = hexFormatter.formatHex(sessionTokenBytes);

        sessions.put(sessionToken, userName);

        String setCookieHeaderValue = String.format("session=%s; Path=/; HttpOnly; SameSite=Strict;", sessionToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", setCookieHeaderValue);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).location(URI.create("/cart")).build();
    }

    @GetMapping("/login")
    public String home() {
        return "Welcome to the protected page!";
    }
}

