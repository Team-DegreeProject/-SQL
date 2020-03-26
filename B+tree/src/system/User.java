package system;

public class User {
    private String userName;
    private int userId;
    public User(){}
    public User(int id,String name){userId=id;userName=name;}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
