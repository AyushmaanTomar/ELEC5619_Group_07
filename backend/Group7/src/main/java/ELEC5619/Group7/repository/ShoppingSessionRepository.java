package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.ShoppingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Integer> {

    @Query("SELECT MAX(s.sessionID) FROM ShoppingSession s")
    Integer findMaxId();
}
