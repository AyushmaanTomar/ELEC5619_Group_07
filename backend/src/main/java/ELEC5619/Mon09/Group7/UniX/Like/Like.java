package ELEC5619.Mon09.Group7.UniX.Like;

public class Like {
    private int likeId;
    private int productId;
    private int userId;

    public Like(int likeId, int productId, int userId) {
        this.likeId = likeId;
        this.productId = productId;
        this.userId = userId;
    }

    public int getId() {
        return likeId;
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

}
