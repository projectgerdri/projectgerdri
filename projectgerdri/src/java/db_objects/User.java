package db_objects;

import java.util.Date;

public class User {
    
    private int userId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String username;
    private String nickname;
    private String password;
    private String avatar;
    private Date registerDate; //No tiene setter
    private boolean state;
    private Date lastLogin;
    private Date deleted;
    
    /*Getters y setters*/
    public int getUserId() {
        return userId;
    }    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Date getRegisterDate() {
        return registerDate;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public Date getDeleted() {
        return deleted;
    }
    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
    /*Fin getters y setters*/
    
    /*El resto de métodos*/
    /*Fin del resto de métodos*/
}
