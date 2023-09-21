package ELEC5619.Mon09.Group7.UniX.User;

abstract class User {

    private int userId;
    private Boolean isAdmin;
    private String email;
    private String username;
    private String password;
    private String phone;

    public User(int userId, Boolean isAdmin, String username, String email, String password, String phone) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    /*
     * User ID 
     */
    public int getUserId() {
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
     * admin 
     */
    public Boolean getisAdmin() {
        return isAdmin;
    }

     /*
     * Emaial, we used it to determine user identity, hence can not be modified 
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
