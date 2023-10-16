package ELEC5619.Mon09.Group7.UniX.User;

public class User {

    private int userId;
    private String email;
    private String username;
    private String password;
    private String phone;

    public User(int userId, String username, String email, String password, String phone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    /*
     * User ID
     */
    public int getId() {
        return userId;
    }

    /*
     * User Name
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * Email, we used it to determine user identity, hence can not be modified
     */
    public String getEmail() {
        return email;
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

    /*
     * Phone
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
