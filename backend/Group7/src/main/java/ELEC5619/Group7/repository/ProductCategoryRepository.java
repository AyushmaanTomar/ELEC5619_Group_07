package ELEC5619.Group7.repository;

import ELEC5619.Group7.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

    @Query("select max(p.categoryID) from ProductCategory p")
    public Integer findMaxId();
}
