package be.laurent_fournier.eparkingbe_v3;

/**
 * Created by Lowlow on 12/11/2014.
 * Version ${VERSION}.
 */

public class User {
    private int userId = 0;
    private String userName = null, userPassword = null;

    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User ID = " + userId + ", User Name = " + userName + ", User Password = " + userPassword;
    }
}
