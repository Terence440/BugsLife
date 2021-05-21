package assignment;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {

    private static Integer projectCount = 0;
    private Integer projectId;
    private String projectName;
    private Date projectTime;
//    private SimpleDateFormat ft; --> Date, set at gson
    List<Issue> issueList = new ArrayList<>();
    //ArrayList<Update> history = new ArrayList<>();                              
    //dunno how to implement update for creating project(diif. class)           

    public Project(String projectName) {
        this.projectName = projectName;
        projectId = ++projectCount;
        projectTime = new Date();
        //history.add(new Update(projectName, this.getProjectTime()));
    }
    
    public void createIssue(String issueTitle, String issueDescrip, String tag, int priority, String status, String creatorUser, String assigneeUser) {
        issueList.add(new Issue(issueTitle, issueDescrip, tag, priority, status, creatorUser, assigneeUser));
    }
    
    
    public void printAllProjects() {
        System.out.println(this.getProjectId() + "\t\t" + this.getProjectName() + "\t\t" + this.issueList.size() + "\n");
    }

    public void printAllIssues() {
        for (Issue i : issueList) {
            i.printSingleIssue();
        }
    }
    
    
    /**
     * search issueTitle,issueDescription,issueComment for matching keywords
     *
     * @param searchString keyword to be searched
     * @return ArrayList<Issue>
     */
    
    
    public List<Issue> searchIssue(String searchString) {
        List<Issue> match = new ArrayList<>();
        for (Issue i : issueList) {
            if (isContain(i.getIssueTitle(), searchString) || isContain(i.getIssueDescrip(), searchString) 
                    || isCommentContain(i.getListComment(), searchString)) {
                match.add(i);
            }
        }
        return match;
    }
    
    
    /**
     * sort issue according to criteria in String
     * only sorted for display(no changes on the actual position in issueList)
     * @param sortType criteria to sort
     */
    
    
    public void sortIssue(String sortType) {
        issueList.get(0).sortType = sortType;                    //sortType is static and is created for determining which statement to use in compareTo() 
        PriorityQueue<Issue> p = new PriorityQueue(issueList);   //create temporary PriorityQueue to automatically sort according to criteria               
        while (!p.isEmpty()) {
            p.poll().printSingleIssue();                         //remove head element from the PriorityQueue(already sorted) and print it one by one until empty
        }
    }

    private boolean isCommentContain(ArrayList<Comment> comment, String searchString) {   //to check for exact string in an ArrayList<Comment>(local method)
        for (Comment c : comment) {
            if (isContain(c.getCommentText(), searchString)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContain(String fullText, String searchString) {                 //to check for exact string in the source string(local method)
        String pattern = "\\b" + searchString + "\\b";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);               //make the keyword case insensitive
        Matcher m = p.matcher(fullText);
        return m.find();
    }
    
    
    public List<Issue> getIssueList() {
        return issueList;
    }
    
    public Issue getIssue(int issueId) {
        for (int i = 0; i < issueList.size(); i++) {
            if (issueId == issueList.get(i).getIssueID()) {
                return issueList.get(i);
            }
        }return null;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
    
    //** Changed **
//    public String getProjectTime() {
//        ft = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
//        return ft.format(projectTime);
//    }
    
    public Date getProjectTime(){
        return projectTime;
    }
}