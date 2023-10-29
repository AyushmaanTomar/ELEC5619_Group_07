package ELEC5619.Group7;

import ELEC5619.Group7.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    // @Test
    // void testGetSetId() {
    //     user.setId(1);
    //     assertEquals(1, user.getId());
    // }

    // @Test
    // void testGetSetPassword() {
    //     user.setPassword("password123");
    //     assertEquals("password123", user.getPassword());
    // }

    // @Test
    // void testGetSetEmail() {
    //     user.setEmail("test@example.com");
    //     assertEquals("test@example.com", user.getEmail());
    // }

    // @Test
    // void testGetSetUserName() {
    //     user.setUserName("John Doe");
    //     assertEquals("John Doe", user.getUserName());
    // }

    // @Test
    // void testGetSetPhone() {
    //     user.setPhone("123-456-7890");
    //     assertEquals("123-456-7890", user.getPhone());
    // }

    // @Test
    // void testToString() {
    //     user.setId(1);
    //     user.setUserName("John Doe");
    //     user.setPassword("password123");
    //     user.setEmail("test@example.com");
    //     user.setPhone("123-456-7890");

    //     String expected = "User{id=1, name='John Doe', password='password123', email='test@example.com', phone='123-456-7890'}";
    //     assertEquals(expected, user.toString());
    // }
}

