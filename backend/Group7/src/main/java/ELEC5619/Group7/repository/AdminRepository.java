package ELEC5619.Group7.repository;


import ELEC5619.Group7.entity.Admin;
import ELEC5619.Group7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

    @Query("select max(a.id) from Admin a")
    public Integer findMaxId();


    @Query("select a.userName from Admin a")
    public List<String> findAllUserName();

    @Modifying
    @Query(value = "delete from User where userName = 1?", nativeQuery = true)
    void deleteByUserID(String userName);


}
