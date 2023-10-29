package ELEC5619.Group7.service;


import ELEC5619.Group7.entity.Admin;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public String createAdmin(Admin admin) {

        if (!isValidPassword(admin.getPassword())) {
            return "invalid_password";
        }

        if (isValidName(admin.getUserName())) {
            return "invalid_username";
        }

        try {
            admin.setId(null == adminRepository.findMaxId() ? 0 : adminRepository.findMaxId() + 1);
            adminRepository.save(admin);
            return "success";
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Admin> readAdmin() {
        return adminRepository.findAll();
    }
    private boolean isValidPassword(String password) {
        if (password.length() < 6 || password.length() > 20)
            return false;
        Pattern specialCharPatten = Pattern.compile("[^a-zA-Z0-9+]");
        Matcher matcher = specialCharPatten.matcher(password);
        if (!matcher.find())
            return false;
        return true;
    }


    private boolean isValidName(String name) {
        List<String> names = adminRepository.findAllUserName();
        return names.contains(name);
    }


    public String authenticateAdmin(String userName, String password) {
        List<Admin> admins = adminRepository.findAll();

        if (isValidName(userName) == false) {
            return "user_not_found";
        }

        for (Admin i: admins) {
            if (i.getUserName().equals(userName) && i.getPassword().equals(password)) {
                return "Authenticated successfully";
            }
        }

        return "incorrect_password";
    }



}
