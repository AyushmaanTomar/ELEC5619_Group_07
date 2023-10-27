package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public String createStudent(User user) {

        if (!isValidPassword(user.getPassword())) {
            return "invalid_password";
        }

        if (!isValidEmail(user.getEmail())) {
            return "invalid_email";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return "email_exists";
        }

        try {
            user.setId(null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1);
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            throw e;
        }
    }


    public List<User> readUser() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).get(0);
    }

    public Map<String, String> login (String email, String password) {
        Map<String, String> result = new HashMap<>();
        result.put("status", "0");
        result.put("message", "Failed to log in: " + email);

        if (!userRepository.existsByEmail(email)) {
            return result;
        }

        User user = userRepository.findByEmail(email).get(0);
        if (password.equals(user.getPassword())) {
            String jwt = jwtService.generateToken(user);
            result.put("message", "Successfully logged in");
            result.put("token", jwt);
        }

        return result;
    }

    @Transactional
    public String updateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            try {
                List<User> students = userRepository.findByEmail(user.getEmail());
                students.stream().forEach(s -> {
                    User userToBeUpdate = userRepository.findById(s.getId()).get();
                    userToBeUpdate.setName(user.getName());
                    userToBeUpdate.setPassword(user.getPassword());
                    userToBeUpdate.setPhone(user.getPhone());
                    userRepository.save(userToBeUpdate);
                });
                return "updated";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "student_not_found";
        }
    }

    @Transactional
    public String deleteUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            try {
                List<User> students = userRepository.findByEmail(user.getEmail());
                students.forEach(userRepository::delete);
                return "deleted";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "student_not_found";
        }
    }


    public String authenticateUser(User user) {
        List<User> users = userRepository.findByEmail(user.getEmail());
        if (users.isEmpty()) {
            return "user_not_found";
        }
        if (user.getPassword().equals(users.get(0).getPassword())) {
            return "Authenticated successfully";
        }
        return "incorrect_password";
    }

    public String changePassword(User user, String newPassword) {
        // Check if the new password is valid
        if (!isValidPassword(newPassword)) {
            return "invalid_password";
        }

        List<User> users = userRepository.findByEmail(user.getEmail());
        if (!users.isEmpty()) {
            User existingUser = users.get(0);
            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);
            return "success";
        }
        return "update_failure";
    }


    private boolean isValidPassword(String password) {
        if (password.length() < 6 || password.length() > 20)
            return false;
        Pattern specialCharPatten = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = specialCharPatten.matcher(password);
        if (!matcher.find())
            return false; // doesn't have a special character
        return true;
    }

    private boolean isValidEmail(String email) {
        return email.endsWith("@uni.sydney.edu.au");
    }

    public User getUserById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Transactional
    public String setUserProfileImage(Integer userId, String imagePath) {
        try {
            User user = userRepository.getOne(userId);
            if (user != null) {
                user.setProfileImg(imagePath);
                userRepository.save(user);
                return "Profile image updated successfully.";
            } else {
                return "User not found.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUserProfileImage(Integer userId) {
        try {
            User user = userRepository.getOne(userId);
            if (user != null) {
                return user.getProfileImg();
            } else {
                return "User not found.";
            }
        } catch (Exception e) {
            throw e;
        }
    }
}