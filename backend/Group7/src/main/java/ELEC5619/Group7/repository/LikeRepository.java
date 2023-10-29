package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.LikeTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeTo, Integer> {

    @Query("SELECT MAX(l.likeID) FROM LikeTo l")
    Integer findMaxId();

    @Query(value = "select userID from LikeTo where itemID = ?1", nativeQuery = true)
    List<Integer> getUserIDWithItem(Integer itemID);

    @Query(value = "select itemID from LikeTo where userID = ?1", nativeQuery = true)
    List<Integer> getItemIDWithUser(Integer userID);
}