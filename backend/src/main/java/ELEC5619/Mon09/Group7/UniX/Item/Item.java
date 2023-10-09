package ELEC5619.Mon09.Group7.UniX.Item;

import java.util.Date;

public class Item {
    private int itemID;
    private int categoryId;
    private int userId;
    private String name;
    private double price;
    private int qty;
    private int likeAmount;
    private String description;
    private Date listsingDate;
    private boolean isSold;

    public Item(int itemId, int categoryId, int userId, String name,
            double price, String description, int qty, Date date,
            int likeAmount, boolean isSold) {
        this.itemID = itemId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.qty = qty;
        this.listsingDate = date;
        this.likeAmount = likeAmount;
        this.isSold = isSold;
    }

    /*
     * Item, Noted, itemID is unique and cant be changed
     */
    public int getItemID() {
        return this.itemID;
    }

    /*
     * categoryId
     */
    public int getCategoryID() {
        return this.categoryId;
    }

    public void setCategoryID(int newID) {
        this.categoryId = newID;
    }

    /*
     * UserID
     */
    public int getUserID() {
        return this.userId;
    }

    public void setUserID(int newID) {
        this.userId = newID;
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
     * Price
     */
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
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

    /*
     * Qty
     */
    public int getQty() {
        return this.qty;
    }

    public void setQty(int newQty) {
        this.qty = newQty;
    }
    /*
     * ListsingDate
     */
    public Date getListingDate() {
        return this.listsingDate;
    }

    public void setListingDate(Date newDate) {
        this.listsingDate = newDate;
    }
    
    /*
     * LikeAmount
     */
    public int getLikeAmount () {
        return this.likeAmount;
    }

    public void setLikeAmount(int newLikeAmount) {
        this.likeAmount = newLikeAmount;
    }
    /*
     * isSold
     */

     public boolean isSoldItem() {
        return this.isSold;
     }

     public void sold() {
        this.isSold = true;
     }

}