package ELEC5619.Mon09.Group7.UniX.ProductCategory;

public class ProductCategory {
    private int categoryID;
    private String name;
    private String description;

    public ProductCategory(int categoryID, String name, String description) {
        this.categoryID = categoryID;
        this.name = name; 
        this.description = description;
    }

    /*
     * CategoryID
     */
    public int getCategoryID() {
        return this.categoryID;
    }

    /*
     * Name
     */
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    /*
     * Description
     */
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
