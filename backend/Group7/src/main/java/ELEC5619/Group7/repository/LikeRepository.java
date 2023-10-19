package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    @Query("SELECT MAX(l.likeID) FROM Like l")
    Integer findMaxId();
}
