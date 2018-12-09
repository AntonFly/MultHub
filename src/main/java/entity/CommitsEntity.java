package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commits", schema = "public", catalog = "multhub")
public class CommitsEntity {
    private String projectid;
    private String developer;
    private Timestamp time;
    private Approved approved;
    private String id;

    @Basic
    @Column(name = "projectid", nullable = false, length = -1)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "developer", nullable = false, length = 30)
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "approved", nullable = true)
    public Approved getApproved() {
        return approved;
    }

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    @Id
    @Column(name = "id", nullable = false, length = -1)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitsEntity that = (CommitsEntity) o;
        return Objects.equals(projectid, that.projectid) &&
                Objects.equals(developer, that.developer) &&
                Objects.equals(time, that.time) &&
                Objects.equals(approved, that.approved) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectid, developer, time, approved, id);
    }
}
