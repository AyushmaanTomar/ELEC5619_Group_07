package ELEC5619.Group7.entity;

import javax.persistence.*;
import java.lang.constant.Constable;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "userID", unique = true)
    private int id;

    @Column(name = "userName")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_pic_id", referencedColumnName = "id")
    private ProfilePic profilePic;

    public User() {
        // Assign default profile pic when a user is created
        this.profilePic = new ProfilePic();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                "}";
    }
}

@Entity
@Table(name = "ProfilePic")
class ProfilePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String filePath;

    public ProfilePic() {
        // Default profile picture file path
        this.filePath = "../resources/static/default.jpg";
    }

    public String getProfilePic(){
        return filePath;
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
}