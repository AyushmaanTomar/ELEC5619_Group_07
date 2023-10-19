package ELEC5619.Group7.entity;


import javax.persistence.*;

@Entity
@Table(name = "ShoppingSession")
public class ShoppingSession {
    @Id
    @Column(name = "sessionID")
    private int sessionID;

    @OneToOne(optional = false)
    @JoinColumn(name = "userID", nullable = false, insertable = false, updatable = false)
    private User buyerUserID;

    @OneToOne(optional = false)
    @JoinColumn(name = "userID", nullable = false, insertable = false, updatable = false)
    private User sellerUserID;

    @OneToOne(optional = false)
    @JoinColumn(name = "itemID", nullable = false)
    private Item item;

    private int total;

    public int getId() {
        return sessionID;
    }

    public void setId(int id) {
        this.sessionID = id;
    }

    public User getBuyerUserID() {
        return buyerUserID;
    }

    public void setBuyerUserID(User buyerUserID) {
        this.buyerUserID = buyerUserID;
    }

    public User getSellerUserID() {
        return sellerUserID;
    }

    public void setSellerUserID(User sellerUserID) {
        this.sellerUserID = sellerUserID;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

