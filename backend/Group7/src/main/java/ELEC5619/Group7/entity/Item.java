package ELEC5619.Group7.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "itemID")
    private int itemID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoryID", nullable = false)
    private ProductCategory productCategory;

    @OneToOne(optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "qty")
    private int qty;

    @Column(name = "LikeAmount")
    private int likeAmount;

    @Column(name = "description")
    private String description;

    @Column(name = "ListingDate ")
    // sql has no datetype
    private String listingDate;

    @Column(name = "isSold")
    private boolean isSold;

    @Column(name = "img_path")
    private String profile_pic;

    public Item() {

    }

    public int getId() {
        return itemID;
    }

    public void setId(int id) {
        this.itemID = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(int likeAmount) {
        this.likeAmount = likeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public String getProfilePic() {
        return profile_pic;
    }

    public void setProfilePic(String file_path) {
        this.profile_pic = file_path;
    }
}
