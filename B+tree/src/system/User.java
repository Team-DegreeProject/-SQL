package system;

public class User {
    private String userName;
    private int userId;
    private UserAccessedTable userAccessedTable;
    public User(){}
    public User(int id,String name){
        userId=id;
        userName=name;
        try {
            userAccessedTable=new UserAccessedTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public UserAccessedTable getUserAccessedTable() { return userAccessedTable; }

    public void setUserAccessedTable(UserAccessedTable usa) {
        this.userAccessedTable = usa;
    }
}
