package ELEC5619.Group7.entity;


import javax.persistence.*;

@Entity
@Table(name = "LikeTo")
public class LikeTo {
    @Id
    @Column(name = "likeID")
    private int likeID;

    @OneToOne
    @JoinColumn(name = "itemID")
    private Item item;

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    public int getLike() {
        return likeID;
    }

    public void setLike(int like) {
        this.likeID = like;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
