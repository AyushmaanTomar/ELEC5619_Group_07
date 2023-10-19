package ELEC5619.Group7.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory {
    @Id
    @Column(name = "categoryID")
    private int categoryID;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public ProductCategory() {

    }

    public int getId() {
        return categoryID;
    }

    public void setId(int id) {
        this.categoryID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
