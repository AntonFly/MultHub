package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "requests", schema = "public", catalog = "multhub")
@IdClass(RequestsEntityPK.class)
public class RequestsEntity {
    private String login;
    private int projectid;
    private Projpos projpos;
    private Boolean isrequest;
    private UsersEntity usersByLogin;
    private ProjectsEntity projectsByProjectid;

    @Id
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    @Column(name = "projectid", nullable = false)
    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public void setProjpos(Projpos projpos) {
        this.projpos = projpos;
    }

    @Basic
    @Column(name = "projpos", nullable = true)
    public Projpos getProjpos() {
        return projpos;
    }

    public void setProjpos(Projpos projpos) {
        this.projpos = projpos;
    }

    @Basic
    @Column(name = "isrequest", nullable = true)
    public Boolean getIsrequest() {
        return isrequest;
    }

    public void setIsrequest(Boolean isrequest) {
        this.isrequest = isrequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestsEntity that = (RequestsEntity) o;
        return projectid == that.projectid &&
                Objects.equals(login, that.login) &&
                Objects.equals(projpos, that.projpos) &&
                Objects.equals(isrequest, that.isrequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, projectid, projpos, isrequest);
    }

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false)
    public UsersEntity getUsersByLogin() {
        return usersByLogin;
    }

    public void setUsersByLogin(UsersEntity usersByLogin) {
        this.usersByLogin = usersByLogin;
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
