
package assignment;

import java.util.Date;


public class Update {
    private String commentU;
    private String statusU;
    private String titleU;
    private Date time;
    private String statusB;
    private String projectNameU;
    private char c;
    private int commentIdU;
    private int updateNumber;
    private static int updateCount = 0;

    public Update(int commentIdU, String commentU, Date time) {    //update comment
        this.commentU = commentU;
        this.commentIdU = ++commentIdU;
        this.time = time;
        updateNumber = ++updateCount;
    }

    public Update(String titleU, Date time, char c) {  //creating issue
        this.titleU = titleU;
        this.c = c;
        this.time = time;
        updateNumber = ++updateCount;
    }

    public Update(String projectNameU, Date time) {   //creating project
        this.time = time;
        this.projectNameU = projectNameU;
        updateNumber = ++updateCount;
    }

    public Update(String statusB, String statusU, Date time) {    //update status
        this.statusU = statusU;
        this.time = time;
        this.statusB = statusB;
        updateNumber = ++updateCount;
    }

    public String getProjectNameU() {
        return projectNameU;
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public Date getTime() {
        return time;
    }

    public String getStatusB() {
        return statusB;
    }

    public String getTitleU() {
        return titleU;
    }

    public char getC() {
        return c;
    }

    public String getCommentU() {
        return commentU;
    }

    public String getStatusU() {
        return statusU;
    }

    public int getCommentIdU() {
        return commentIdU;
    }

}
