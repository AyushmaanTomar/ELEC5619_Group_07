package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select max(i.itemID) from Item i")
    public Integer findMaxId();
}
