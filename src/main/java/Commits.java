import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commits")
public class Commits {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_projectid")
    private int projectid;
    @Id
    private String developer;
    private String filedirectory;
    @Id
    private LocalDateTime  time;

    @Enumerated(EnumType.STRING)
    private Approved approved;

    public Commits(){}

    public Commits(int projectid, String developer, String filedirectory, LocalDateTime time, Approved approved) {
        this.projectid = projectid;
        this.developer = developer;
        this.filedirectory = filedirectory;
        this.time = time;
        this.approved = approved;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setFiledirectory(String filedirectory) {
        this.filedirectory = filedirectory;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    public int getProjectid() {
        return projectid;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getFiledirectory() {
        return filedirectory;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Approved getApproved() {
        return approved;
    }

    @Override
    public String toString(){
        return projectid+" "+developer;
    }
}
