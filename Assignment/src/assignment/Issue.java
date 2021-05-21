package assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Issue implements Comparable<Issue> {

    private static Integer issueCount = 0;
    private Integer issueID;
    private Integer priority;
    private String issueTitle;
    private String issueDescrip;
    private String creatorUser;
    private String assigneeUser;
    private String tag;
    private String status;
    protected static String sortType;
//    private SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss"); --> Date, set at gson
    private Date timestampIssue;
    ArrayList<Comment> listComment = new ArrayList<>();
    List<Update> historyIssue = new ArrayList<>();

    public Issue() {
    }
    
    public Issue(String issueTitle, String issueDescrip, String tag, Integer priority, String status, String creatorUser, String assigneeUser) {
        issueCount++;
        this.issueID = issueCount;
        this.priority = priority;
        this.issueTitle = issueTitle;
        this.issueDescrip = issueDescrip;
        this.creatorUser = creatorUser;
        this.assigneeUser = assigneeUser;
        this.tag = tag;
        this.status = status;
        this.timestampIssue = new Date();
        historyIssue.add(new Update(issueTitle, this.getTimestampIssue(), 'C'));
    }

    public void addComment(String cUser, String cText) {
        Comment c = new Comment(cUser, cText);
        listComment.add(c);
    }

    public void addReaction(int commentNum, String s) {
        listComment.get(commentNum - 1).increaseReactionCount(s);
    }
    

    public void printSingleIssue() { //print in console
        System.out.println(this.getIssueID() + " | " + this.getIssueTitle() + " | " + this.getStatus() + " | " + this.getTag() + " | \t" + this.getPriority() + " | " + this.getTimestampIssue() + " | " + this.getAssigneeUser() + "\t | " + this.getCreatorUser() + "\t|\n");   //write in main
    }

    public String getIssuePage() {
        String s = "Issue ID: " + this.getIssueID() + "\tStatus: " + this.getStatus()
                + "\nTag: " + this.getStatus() + "\tPriority: " + this.getPriority() + "\tCreated On: " + this.getTimestampIssue()
                + "\nTitle: " + this.getIssueTitle()
                + "\nAssigned to: " + this.getAssigneeUser() + "\t\tCreated By: " + this.getCreatorUser()
                + "\nIssue Description\n----------------\n"
                + this.getIssueDescrip() + "\n";
        return s;
    }

    public String getCommentPage() {
        String s = "Comments\n---------\n";
        if (!listComment.isEmpty()) {
            for (Comment c : listComment) {
                s += c.toString();
            }
        } else {
            s += "No Comments :(";
        }
        return s;
    }

    public void updateStatus(String newStatus) {
        String currentStatus = this.getStatus();
        Date time = this.getTimestampIssue();
        if (canSetStatus(newStatus)) {
            historyIssue.add(new Update(currentStatus, newStatus, time));
            this.status = newStatus;
        } else {
            System.out.println("There's no such option!");
        }
    }

    public void updateComment(int Id, String newComment) {
        int id = Id - 1;
        if (id >= 0 && id < listComment.size()) {
            Date time = this.getTimestampIssue();
            historyIssue.add(new Update(id, newComment, time));
            this.listComment.get(id).commentText = newComment;
        } else {
            System.out.println("Invalid Comment Id!");
        }
    }

    public void showHistory() {
        System.out.println("Activities History");
        System.out.println("-----------------------------------------\nLatest\n------");  //seperation line
        for (int i = historyIssue.size() - 1; i >= 0; i--) {
            if (historyIssue.get(i).getTitleU() != null) {                                 //if have title stored(issue created)
                System.out.println((i+1) + "# Created new issue with the title of \"" + historyIssue.get(i).getTitleU() + "\"\nat " + historyIssue.get(i).getTime());
            } else if (historyIssue.get(i).getStatusU() != null) {                           //if have status stored(status updated)
                System.out.println((i+1) + "# Updated issue status\nfrom \"" + historyIssue.get(i).getStatusB() + "\" to \"" + historyIssue.get(i).getStatusU() + "\"\nat " + historyIssue.get(i).getTime());
            } else if (historyIssue.get(i).getCommentU() != null) {                               //if have comment stored(comment updated)
                System.out.println((i+1) + "# Updated issue comment of\nID: " + historyIssue.get(i).getCommentIdU() + "\nComment updated: " + historyIssue.get(i).getCommentU() + "\nat " + historyIssue.get(i).getTime());
            } else if (historyIssue.get(i).getProjectNameU() != null) {                               //if have project name stored(project created)
                System.out.println(historyIssue.get(i).getUpdateNumber() + "# Created new project with the title of \"" + historyIssue.get(i).getProjectNameU() + "\"\nat " + historyIssue.get(i).getTime());
            }
            System.out.println("------------------------------------------");
        }
        System.out.println("Oldest\n-------\n-----------------------------------------\n");
    }

    private boolean canSetStatus(String s) {
        boolean flag = false;
        if (this.getStatus().equalsIgnoreCase("Open") && (s.equalsIgnoreCase("In Progress") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("In Progress") && (s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved") || s.equalsIgnoreCase("Open"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Resolved") && (s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Reopened"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Reopened") && (s.equalsIgnoreCase("Resolved") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("In Progress"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Closed") && s.equalsIgnoreCase("Reopen")) {
            flag = true;
        }
        return flag;
    }

    public void combinedIssueSection() {
        System.out.println(this.getIssuePage() + this.getCommentPage());

    }

    public int compareTo(Issue i) {
        if (sortType.equalsIgnoreCase("Status")) {
            return this.getStatus().compareTo(i.getStatus());
        } else if (sortType.equalsIgnoreCase("Id")) {
            return i.getIssueID() < this.getIssueID() ? 1 : -1;
        } else if (sortType.equalsIgnoreCase("Priority")) {
            return i.getPriority() < this.getPriority() ? 1 : -1;
        } else if (sortType.equalsIgnoreCase("Tag")) {
            return this.getTag().compareTo(i.getTag());
        } else if (sortType.equalsIgnoreCase("Time")) {
            return i.getTimestampIssue().compareTo(this.getTimestampIssue());
        } else {
            return 0;
        }
    }

    public ArrayList<Comment> getListComment() {
        return listComment;
    }
    
    public int getIssueID() {
        return issueID;
    }

    public int getPriority() {
        return priority;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public String getIssueDescrip() {
        return issueDescrip;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public String getAssigneeUser() {
        return assigneeUser;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }
    
    /** Changed **
    public String getTimestampIssue() {
        return ft.format(timestampIssue);
    }
    */
    
    public Date getTimestampIssue(){
        return timestampIssue;
    }

    /*same as printSingleIssue()
    public String toString() {
        return this.getIssueNum() + " | " + this.getIssueTitle() + " | " + this.getStatus() + " | " + this.getTag() + " | \t" + this.getPriority() + " | " + this.getTimestampIssue() + " | " + this.getAssigneeUser() + "\t | " + this.getCreatorUser() + "\t|\n";   //write in main
    }
    /*
    public String deleteComment(){
        
    }
    */


}
