package ELEC5619.Group7.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "itemID", unique = true)
    private int itemID;

    @OneToOne(optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "qty")
    private int qty;

    @Column(name = "LikeAmount")
    private int likeAmount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "ListingDate ")
    // sql has no datetype
    private String listingDate;

    @Column(name = "isSold")
    private boolean isSold;

    @Column(name = "imagePath", nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'default_image_path.jpg'")
    private String imagePath;

    public Item() {
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return itemID;
    }

    public void setId(int id) {
        this.itemID = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.itemID +
                ", name='" + this.name + '\'' +
                ", user='" + this.user.getId() + '\'' +
                ", price='" + this.price + '\'' +
                ", QTY='" + this.qty + '\'' +
                ", like Amount='" + this.likeAmount + '\'' +
                ", description ='" + this.description + '\'' +
                ", Listing Date ='" + this.likeAmount + '\'' +
                "}";
    }
}
