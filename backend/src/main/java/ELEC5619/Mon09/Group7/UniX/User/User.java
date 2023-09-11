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

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getisAdmin() {
        return isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
