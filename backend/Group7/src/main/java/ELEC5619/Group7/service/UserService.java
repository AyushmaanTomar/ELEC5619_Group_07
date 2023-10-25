package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String createStudent(User user) {

        if (!isValidPassword(user.getPassword())) {
            return "Password does not meet the requirements.";
        }

        if (!isValidEmail(user.getEmail())) {
            return "Email is not a valid university email.";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists.";
        }

        try {
            if (!userRepository.existsByEmail(user.getEmail())) {
                user.setId(null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1);
                userRepository.save(user);
                return "Student record created successfully.";
            } else {
                return "Student already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<User> readUser() {
        return userRepository.findAll();
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
                return "Student record updated.";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "Student does not exist in the database.";
        }
    }

    @Transactional
    public String deleteUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            try {
                List<User> students = userRepository.findByEmail(user.getEmail());
                students.stream().forEach(s -> {
                    userRepository.delete(s);
                });
                return "Student record deleted successfully.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "Student does not exist";
        }
    }

    public String authenticateUser(User user) {
        List<User> users = userRepository.findByEmail(user.getEmail());
        if (!users.isEmpty() && user.getPassword().equals(users.get(0).getPassword())) {
            return "Authenticated successfully";
        }
        return "Authentication failed";
    }

    public String changePassword(User user, String newPassword) {
        // Check if the new password is valid
        if (!isValidPassword(newPassword)) {
            return "Password does not meet the requirements.";
        }

        List<User> users = userRepository.findByEmail(user.getEmail());
        if (!users.isEmpty()) {
            User existingUser = users.get(0);
            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);
            return "Password updated successfully";
        }
        return "Failed to update password";
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
}
