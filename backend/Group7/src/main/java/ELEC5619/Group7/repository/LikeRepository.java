package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    @Query("SELECT MAX(l.likeID) FROM Like l")
    Integer findMaxId();

    @Query(value = "select userid from Like where itemid = ?1", nativeQuery = true)
    List<Integer> getUserIDWithItem(Integer itemID);

    @Query(value = "select itemid from Like where userid = ?1", nativeQuery = true)
    List<Integer> getItemIDWithUser(Integer userID);

    @Modifying
    @Query(value = "delete from Like where itemid = ?1 and userid = ?2", nativeQuery = true)
    void deleteByItemIdAndUserId(Integer itemId, Integer userId);

}
