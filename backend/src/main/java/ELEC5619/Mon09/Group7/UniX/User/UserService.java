// package ELEC5619.Mon09.Group7.UniX.User;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class UserService {
//     @Autowired
//     private UserRepository userRepository;

//     // Create a new user
//     public User createUser(User user) {
//         return userRepository.save(user);
//     }

//     // Get all users
//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     // Get user by ID
//     public Optional<User> getUserById(Long id) {
//         return userRepository.findById(id);
//     }

//     // Update username
//     public User updateUsername(Long id, String newUsername) {
//         Optional<User> user = userRepository.findById(id);
//         if (user.isPresent()) {
//             User existingUser = user.get();
//             existingUser.setUsername(newUsername);
//             return userRepository.save(existingUser);
//         }
//         return null;
//     }

//     // Update password
//     public User updatePassword(Long id, String newPassword) {
//         Optional<User> user = userRepository.findById(id);
//         if (user.isPresent()) {
//             User existingUser = user.get();
//             existingUser.setPassword(newPassword);
//             return userRepository.save(existingUser);
//         }
//         return null;
//     }

//     // Update phone
//     public User updatePhone(Long id, String newPhone) {
//         Optional<User> user = userRepository.findById(id);
//         if (user.isPresent()) {
//             User existingUser = user.get();
//             existingUser.setPhone(newPhone);
//             return userRepository.save(existingUser);
//         }
//         return null;
//     }

//     // Delete all users
//     public void deleteAllUsers() {
//         userRepository.deleteAll();
//     }

//     // Delete user
//     public void deleteUser(Long id) {
//         userRepository.deleteById(id);
//     }

// }