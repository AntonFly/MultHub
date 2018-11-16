import entity.Commits;

import javax.persistence.*;
import java.util.*;

@Entity
@Table (name = "users")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectid;
    private String name;
    private String description;
    private long curbudget;
    private long goalbudget;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commits> commits;

    public Projects(){}

    public Projects(String name, String description, long curbudget, long goalbudget)
    {
        this.name = name;
        this.description = description;
        this.curbudget = curbudget;
        this.goalbudget = goalbudget;
        commits = new ArrayList();
    }

    public void addCommit(Commits commit) //developer
    {
        commit.setProjectid(projectid);
        commits.add(commit);
    }
    public void removeCommit(Commits commit){
        commits.remove(commit);
    }

    public int getId() {
        return projectid;
    }

    public long getGoalbudget() {
        return goalbudget;
    }
    public long getCurbudget() {

        return curbudget;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurbudget(long curbudget) {
        this.curbudget = curbudget;
    }

    public void setGoalbudget(long goalbudget) {
        this.goalbudget = goalbudget;
    }

    @Override
    public String toString()
    {
        return "Projects{\nid="+projectid+",\nname="+name+",\ndescription="
                +description+",\ncurbudget="+curbudget+",\ngoalbudget="+goalbudget;
    }

}
