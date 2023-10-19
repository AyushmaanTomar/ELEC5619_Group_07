package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
            return "Student does not exists in the database.";
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
