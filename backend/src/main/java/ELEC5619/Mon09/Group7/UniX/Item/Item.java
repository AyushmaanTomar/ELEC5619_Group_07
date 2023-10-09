package ELEC5619.Mon09.Group7.UniX.Item;

import java.time.LocalDateTime;

public class Item {
    private int itemId;
    private int categoryId;
    private int userId;
    private String name;
    private float price;
    private String description;
    private int qty;
    private LocalDateTime date;
    private int likeAmount;
    private boolean isSold;

    public Item(int itemId, int categoryId, int userId, String name,
            float price, String description, int qty, LocalDateTime date,
            int likeAmount, boolean isSold) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.qty = qty;
        this.date = date;
        this.likeAmount = likeAmount;
        this.isSold = isSold;
    }

}