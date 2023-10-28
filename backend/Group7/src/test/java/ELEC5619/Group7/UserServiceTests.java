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
        newUser.setEmail("test@example.com");

        Mockito.when(userRepository.existsByEmail(newUser.getEmail()) > 0).thenReturn(false);
        Mockito.when(userRepository.findMaxId()).thenReturn(0);
        
        String result = userService.createStudent(newUser);
        assertEquals("Student record created successfully.", result);

    }

    @Test
    void testCreateStudentAlreadyExists() {
        User existingUser = new User();
        existingUser.setEmail("test@example.com");

        Mockito.when(userRepository.existsByEmail(existingUser.getEmail()) > 0).thenReturn(true);

        String result = userService.createStudent(existingUser);
        assertEquals("Student already exists in the database.", result);
    }

    @Test
    void testUpdateUser() {
        User userToUpdate = new User();
        userToUpdate.setEmail("test@example.com");
        userToUpdate.setName("Updated Name");
        userToUpdate.setPassword("newPassword");
        userToUpdate.setPhone("1234567890");

        List<User> users = new ArrayList<>();
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail("test@example.com");
        existingUser.setName("Original Name");
        existingUser.setPassword("originalPassword");
        existingUser.setPhone("9876543210");
        users.add(existingUser);

        Mockito.when(userRepository.existsByEmail(userToUpdate.getEmail()) > 0).thenReturn(true);
        Mockito.when(userRepository.findByEmail(userToUpdate.getEmail())).thenReturn(users);
        Mockito.when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));

        String result = userService.updateUser(userToUpdate);
        assertEquals("Student record updated.", result);

        // Check if the user was updated correctly
        assertEquals("Updated Name", existingUser.getName());
        assertEquals("newPassword", existingUser.getPassword());
        assertEquals("1234567890", existingUser.getPhone());
    }

    @Test
    void testUpdateUserNotFound() {
        User userToUpdate = new User();
        userToUpdate.setEmail("nonexistent@example.com");

        Mockito.when(userRepository.existsByEmail(userToUpdate.getEmail()) > 0).thenReturn(false);

        String result = userService.updateUser(userToUpdate);
        assertEquals("Student does not exist in the database.", result);
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

        Mockito.when(userRepository.existsByEmail(userToDelete.getEmail()) > 0).thenReturn(true);
        Mockito.when(userRepository.findByEmail(userToDelete.getEmail())).thenReturn(users);

        String result = userService.deleteUser(userToDelete);
        assertEquals("Student record deleted successfully.", result);

        // Check if the user was deleted
        Mockito.verify(userRepository, Mockito.times(1)).delete(existingUser);
    }

    @Test
    void testDeleteUserNotFound() {
        User userToDelete = new User();
        userToDelete.setEmail("nonexistent@example.com");

        Mockito.when(userRepository.existsByEmail(userToDelete.getEmail()) > 0).thenReturn(false);

        String result = userService.deleteUser(userToDelete);
        assertEquals("Student does not exist", result);
    }
}

