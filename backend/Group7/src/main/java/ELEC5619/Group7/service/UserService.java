package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public String createStudent(User user) {
        try {
            if (!userRepository.existsByEmail(user.getEmail())) {
                user.setId(null == userRepository.findMaxId()? 0 : userRepository.findMaxId() + 1);
                userRepository.save(user);
                return "Student record created successfully.";
            }else {
                return "Student already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<User> readUser(){
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
    public String updateUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
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
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Student does not exist in the database.";
        }
    }

    @Transactional
    public String deleteUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            try {
                List<User> students = userRepository.findByEmail(user.getEmail());
                students.stream().forEach(s -> {
                    userRepository.delete(s);
                });
                return "Student record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Student does not exist";
        }
    }


}
