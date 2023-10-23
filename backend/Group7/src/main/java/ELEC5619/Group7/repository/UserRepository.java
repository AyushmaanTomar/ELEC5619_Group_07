package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public boolean existsByEmail(String email);

    public List<User> findByEmail(String email);

    public boolean existsByUsername(String username);


    @Query("select max(u.id) from User u")
    public Integer findMaxId();
}
