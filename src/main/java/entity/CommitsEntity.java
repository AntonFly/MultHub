package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commits", schema = "public", catalog = "multhub")
@IdClass(CommitsEntityPK.class)
public class CommitsEntity {
    private int projectid;
    private String developer;
    private String filedirectory;
    private Timestamp time;
    private Object approved;
    private ProjectsEntity projectsByProjectid;

    @Id
    @Column(name = "projectid", nullable = false)
    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    @Id
    @Column(name = "developer", nullable = false, length = 30)
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Basic
    @Column(name = "filedirectory", nullable = true, length = -1)
    public String getFiledirectory() {
        return filedirectory;
    }

    public void setFiledirectory(String filedirectory) {
        this.filedirectory = filedirectory;
    }

    @Id
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "approved", nullable = true)
    public Object getApproved() {
        return approved;
    }

    public void setApproved(Object approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitsEntity that = (CommitsEntity) o;
        return projectid == that.projectid &&
                Objects.equals(developer, that.developer) &&
                Objects.equals(filedirectory, that.filedirectory) &&
                Objects.equals(time, that.time) &&
                Objects.equals(approved, that.approved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectid, developer, filedirectory, time, approved);
    }

    @ManyToOne
    @JoinColumn(name = "projectid", referencedColumnName = "projectid", nullable = false)
    public ProjectsEntity getProjectsByProjectid() {
        return projectsByProjectid;
    }

    public void setProjectsByProjectid(ProjectsEntity projectsByProjectid) {
        this.projectsByProjectid = projectsByProjectid;
    }
}
