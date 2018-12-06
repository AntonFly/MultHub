package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comments", schema = "public", catalog = "multhub")
public class CommentsEntity {
    @Generated(GenerationTime.INSERT)
    private int id;
    private Integer projectid;
    private String login;
    private String filedirectory;
    private Timestamp time;
    //private ProjectsEntity projectsByProjectid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "projectid", nullable = true)
    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "login", nullable = true, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "filedirectory", nullable = true, length = -1)
    public String getFiledirectory() {
        return filedirectory;
    }

    public void setFiledirectory(String filedirectory) {
        this.filedirectory = filedirectory;
    }

    @Basic
    @Column(name = "time", nullable = true)
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
        CommentsEntity that = (CommentsEntity) o;
        return id == that.id &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(login, that.login) &&
                Objects.equals(filedirectory, that.filedirectory) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectid, login, filedirectory, time);
    }

//    @ManyToOne
//    @JoinColumn(name = "projectid", referencedColumnName = "projectid")
//    public ProjectsEntity getProjectsByProjectid() {
//        return projectsByProjectid;
//    }
//
//    public void setProjectsByProjectid(ProjectsEntity projectsByProjectid) {
//        this.projectsByProjectid = projectsByProjectid;
//    }
}
