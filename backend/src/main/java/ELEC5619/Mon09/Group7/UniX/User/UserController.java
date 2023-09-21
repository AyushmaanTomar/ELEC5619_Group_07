package ELEC5619.Mon09.Group7.UniX.User;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.net.URI;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.stream.Collectors;


@Controller
public class UserController {
    private final SecureRandom randomNumberGenerator = new SecureRandom();
    private final HexFormat hexFormatter = HexFormat.of();

    List<String> users = null;
    UserDatabaseHelper dbHelper = null;

/**
 * @param userID
 * @param model
 * @return
 */
@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(value = "user", defaultValue = "") int userID, Model model) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).location(URI.create("/cart")).build();
    }
}