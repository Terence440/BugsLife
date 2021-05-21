package assignment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class BugsLife {

    public static void main(String[] args) {

        Project p = new Project("1st Project");                                     //create new project @param : title
        p.createIssue("Can't display the table", "Hi, this is issue description 1", "Frontend", 4, "In Progress", "jhoe", "btan");    //create new issue @param : issueTitle,description,tag,priority,creator,assignee
        p.createIssue("Can't open file", "Hi, this is issue description 2", "Backend", 3, "Open", "Btan", "jhoe");
        p.getProjectTime();
        printProjectDashboard(p);                                                  //print projext dashboard
        Issue i = p.getIssue(1);                                                    //create issue object to modify easily
        printIssuesDashboard(p);                                                    //print issue dashboard using "Project" object(p)
        printSearchResult((ArrayList<Issue>) p.searchIssue("Open"), "Open");                                   //print search result @param : ArrayList<Issue> 
        printSortedResult(p, "Tag");                                                 //sorted by "Tag"
        i.addComment("liew", "Hello, this is 1st comment");                         //add comment into selected issue @param : creator,commentText
        i.addComment("ang", "Hello, this is 2nd comment");
        i.addReaction(1, "Happy");                                                  //add reaction to selected comment Id @param : commentId,reactionType
        i.addReaction(2, "Smile");
        printIssuesSection(i);                                                      //print issue page
        i.updateStatus("Resolved");                                                 //update issue status @param : status to change into
        i.updateComment(1, "Comment Updated 1st try");                              //update comment @param : commentId,textToUpdate
        i.showHistory();                                                            //show activities history
        printIssuesSection(i);                                                      //print issue page
        serialize(p);
    }

    
    public static void printSearchResult(ArrayList<Issue> matched, String keyword) {
        System.out.println("\nSearch Results for \"" + keyword + "\" :");
        if (!matched.isEmpty()) {
            for (Issue m : matched) {
                m.printSingleIssue();
            }
        } else {
            System.out.println("No result found!");
        }
    }

    public static void printSortedResult(Project p, String sortType) {
        System.out.println("\nSorted Result: ");
        System.out.println("ID|\t\tTitle\t\t|\tStatus\t|\tTag\t|Priority|\t\tTime\t\t|Assignee|CreatedBy|");
        p.sortIssue(sortType);
    }

    public static void printProjectDashboard(Project p) {
        System.out.println("\nProject Dashboard: ");
        System.out.println("ID\t\tProject Name\t\tIssues\n");
        p.printAllProjects();
    }

    public static void printIssuesDashboard(Project p) {
        System.out.println("\nIssue Dashboard: ");
        System.out.println("ID|\t\tTitle\t\t|\tStatus\t|\tTag\t|Priority|\t\tTime\t\t|Assignee|CreatedBy|");
        p.printAllIssues();
    }

    public static void printIssuesSection(Issue i) {
        System.out.println("\nIssue Page: ");
        i.combinedIssueSection();
    }
     
    private static void serialize(Project pro) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd kk:mm:ss").create();
        String json = gson.toJson(pro);

        try {
            FileWriter writer = new FileWriter("C:\\Users\\User\\Documents\\NetBeansProjects\\AssignmentNew\\data.json");
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("JSON file successful create.");
    }
}
