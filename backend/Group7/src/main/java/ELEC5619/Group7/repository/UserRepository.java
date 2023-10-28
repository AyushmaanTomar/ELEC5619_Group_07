package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "SELECT EXISTS(SELECT 1 FROM User WHERE email = ?1)", nativeQuery = true)
    public Integer existsByEmail(String email);

    @Query(value = "SELECT * from User where email = ?1", nativeQuery = true)
    public List<User> findByEmail(String email);


    @Query("select max(u.id) from User u")
    public Integer findMaxId();

    Optional<User> findByUserNameAndPassword(String userName, String password);


    //Add User
}
