package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select max(i.itemID) from Item i")
    public Integer findMaxId();

    @Query(value = "select * from Item where userID = ?1", nativeQuery = true)
    List<Item> findAllItemByUser(Integer userID);

    List<Item> findAll();

    @Modifying
    @Query(value = "delete from Item where itemid = ?1", nativeQuery = true)
    void deleteItemByID(Integer itemid);

}
