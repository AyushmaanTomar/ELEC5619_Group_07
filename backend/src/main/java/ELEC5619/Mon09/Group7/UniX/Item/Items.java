package ELEC5619.Mon09.Group7.UniX.Item;

import java.time.LocalDateTime;

public class Items {
    private String itemID;
    private String itemName;
    private float itemPrice;
    private String itemDescription;
    private ItemCategories itemCategories;
    private String sellerID;
    private boolean isSold;
    private LocalDateTime listDate;
    private LocalDateTime soldDate;

    public Items(String itemID, String itemName, float itemPrice, String itemDescription,
            ItemCategories itemCategories, String sellerID, boolean isSold, LocalDateTime listDate,
            LocalDateTime soldDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = this.itemDescription;
        this.itemCategories = this.itemCategories;
        this.sellerID = sellerID;
        this.isSold = isSold;
        this.listDate = listDate;
        this.soldDate = soldDate;
    }

    /**
     * Return Item ID (Primary Key)
     */
    public String getItemID() {
        return this.itemID;
    }

    /**
     * Return Seller ID (Foreign Key)
     */
    public String getSellerID() {
        return this.sellerID;
    }

    /**
     * ItemName
     */
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String newName) {
        this.itemName = newName;
    }

    /**
     * ItemPrice
     */
    public float getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(float newItemPrice) {
        this.itemPrice = newItemPrice;
    }

    /**
     * ItemDescription
     */
    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String newItemDescription) {
        this.itemDescription = newItemDescription;
    }

    /**
     * Category
     */
    public ItemCategories getItemCategories() {
        return this.itemCategories;
    }

    public void setItemCategories(ItemCategories newItemCategories) {
        this.itemCategories = newItemCategories;
    }

    /**
     * isSold
     */
    public boolean getIsSold() {
        return this.isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }

    /**
     * getListDate
     */
    public LocalDateTime getLisDate() {
        return this.listDate;
    }

    public void setListDate(LocalDateTime newListDate) {
        this.listDate = newListDate;
    }

    /**
     * getSoldDate
     */
    public LocalDateTime getSoldDate() {
        return this.soldDate;
    }

    public void setSoldDate(LocalDateTime newSoldDate) {
        this.soldDate = newSoldDate;
    }

}
