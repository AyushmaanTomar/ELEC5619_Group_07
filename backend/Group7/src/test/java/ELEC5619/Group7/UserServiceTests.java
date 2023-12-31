package ELEC5619.Group7;

import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.UserRepository;
import ELEC5619.Group7.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the behavior of UserRepository
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
    }

    @Test
    void testCreateStudent() {
        User newUser = new User();
        newUser.setEmail("test@uni.sydney.edu.au");
        newUser.setPassword("12dj1384yhr8du!");;
        Mockito.when(userRepository.existsByEmail(newUser.getEmail())).thenReturn(0);
        Mockito.when(userRepository.findMaxId()).thenReturn(0);
        
        String result = userService.createStudent(newUser);
        assertEquals("success", result);

    }

    @Test
    void testCreateStudentAlreadyExists() {
        User existingUser = new User();
        existingUser.setEmail("test@example.com");
        existingUser.setUserName("Test User");
        existingUser.setPassword("197yehduicas!");
        Mockito.when(userRepository.existsByEmail(existingUser.getEmail())).thenReturn(1);

        String result = userService.createStudent(existingUser);
        assertEquals("invalid_email", result);
    }

    @Test
    void testUpdateUser() {
        User userToUpdate = new User();
        userToUpdate.setEmail("test@example.com");
        userToUpdate.setUserName("Updated Name");
        userToUpdate.setPassword("newPassword");
        userToUpdate.setPhone("1234567890");

        List<User> users = new ArrayList<>();
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail("test@example.com");
        existingUser.setUserName("Original Name");
        existingUser.setPassword("originalPassword");
        existingUser.setPhone("9876543210");
        users.add(existingUser);

        Mockito.when(userRepository.existsByEmail(userToUpdate.getEmail())).thenReturn(1);
        Mockito.when(userRepository.findByEmail(userToUpdate.getEmail())).thenReturn(users);
        Mockito.when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));

        String result = userService.updateUser(userToUpdate);
        assertEquals("updated", result);

        // Check if the user was updated correctly


        assertEquals("Updated Name", existingUser.getUserName());
        assertEquals("Updated Name", existingUser.getUserName());
        assertEquals("newPassword", existingUser.getPassword());
        assertEquals("1234567890", existingUser.getPhone());
    }

    @Test
    void testUpdateUserNotFound() {
        User userToUpdate = new User();
        userToUpdate.setEmail("nonexistent@example.com");

        Mockito.when(userRepository.existsByEmail(userToUpdate.getEmail())).thenReturn(0);

        String result = userService.updateUser(userToUpdate);
        assertEquals("student_not_found", result);
    }

    @Test
    void testDeleteUser() {
        User userToDelete = new User();
        userToDelete.setEmail("test@example.com");

        List<User> users = new ArrayList<>();
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail("test@example.com");
        users.add(existingUser);

        Mockito.when(userRepository.existsByEmail(userToDelete.getEmail())).thenReturn(1);
        Mockito.when(userRepository.findByEmail(userToDelete.getEmail())).thenReturn(users);


        // Check if the user was deleted
        Mockito.verify(userRepository, Mockito.times(1)).delete(existingUser);
    }

}

