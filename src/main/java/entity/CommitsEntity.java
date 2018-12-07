package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commits", schema = "public", catalog = "multhub")
@IdClass(CommitsEntityPK.class)
public class CommitsEntity {
    private String projectid;
    private String developer;
    private String filedirectory;
    private Timestamp time;
    private Approved approved;

    @Id
    @Column(name = "projectid", nullable = false)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
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
    public Approved getApproved() {
        return approved;
    }

    public void setApproved(Approved approved) {
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

}
