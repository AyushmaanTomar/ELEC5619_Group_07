package ELEC5619.Group7.entity;

import javax.persistence.*;


@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "userID", unique = true)
    private int id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "profileImg", nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'default_image_path.jpg'")
    private String profileImg;

    public User() {
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
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", profileImg='" + profileImg + '\'' +
                "}";
    }
}

@Entity
@Table(name = "ProfilePic")
class ProfilePic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filePath;

    public ProfilePic() {
        // Default profile picture file path
        this.filePath = "../resources/static/default.jpg";
    }

    public String getProfilePic(){
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
