
package assignment;


public class User {
    private static int userCount = 0;
    private int userid;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        userCount++;
        userid = userCount;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
