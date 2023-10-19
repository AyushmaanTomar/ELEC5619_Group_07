package ELEC5619.Mon09.Group7.UniX.ShoppingSession;

public class ShoppingSession {
    private int sessionID;
    private int buyerUserID;
    private int total;
    private int itemID;
    private int sellerUserID;


    public ShoppingSession(int sessionID, int buyerUserID, int itemID, int total, int sellerUserID) {
        this.sessionID = sessionID;
        this.buyerUserID = buyerUserID;
        this.total = total;
        this.itemID = itemID;
        this.sellerUserID = sellerUserID;
    }

    /*
     * SessionID
     */
    public int getSessionID() {
        return this.sessionID;
    }


    /*
     * BuyerUserID
     */
    public int getBuyerUserID() {
        return this.buyerUserID;
    }

    public void setBuyerUserID(int newBuyerUserID) {
        this.buyerUserID = newBuyerUserID;
    }

    /*
     * Total
     */
    public int getTotal() {
        return this.total;
    }

    public void setTotal(int newTotal) {
        this.total = newTotal;
    }

    /*
     * ItemID
     */
    public int getItemID() {
        return this.itemID;
    }

    public void setItemID(int newItemID) {
        this.itemID = newItemID;
    }

    /*
     * SellerUserID
     */

     public int getSellerUserID() {
        return this.sellerUserID;
     }

     public void setSellerUserID(int newSellerID) {
        this.sellerUserID = newSellerID;
     }
}


