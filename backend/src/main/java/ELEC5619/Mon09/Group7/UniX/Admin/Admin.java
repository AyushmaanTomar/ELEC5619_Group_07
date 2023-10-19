package ELEC5619.Mon09.Group7.UniX.Admin;

public class Admin {
    private int adminId;
    private String username;
    private String password;

    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    /*
     * User ID
     */
    public int getId() {
        return adminId;
    }

    /*
     * Username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * Password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
