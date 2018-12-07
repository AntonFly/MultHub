package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class CommitsEntityPK implements Serializable {
    private int projectid;
    private String developer;
    private Timestamp time;

    public CommitsEntityPK(int projectid, String developer, Timestamp time) {
        this.projectid = projectid;
        this.developer = developer;
        this.time = time;
    }

    @Column(name = "projectid", nullable = false, length = -1)
    @Id
    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    @Column(name = "developer", nullable = false, length = 30)
    @Id
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Column(name = "time", nullable = false)
    @Id
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitsEntityPK that = (CommitsEntityPK) o;
        return projectid == that.projectid &&
                Objects.equals(developer, that.developer) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectid, developer, time);
    }
}
