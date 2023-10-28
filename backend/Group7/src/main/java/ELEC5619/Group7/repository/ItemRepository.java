package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select max(i.itemID) from Item i")
    public Integer findMaxId();

    @Query(value = "select * from Item where userID = ?1", nativeQuery = true)
    List<Item> findAllItemByUser(Integer userID);

    @Query(value = "select * from Item where categoryID = ?1", nativeQuery = true)
    List<Item> findAllItemByCategory(Integer categoryID);




}
