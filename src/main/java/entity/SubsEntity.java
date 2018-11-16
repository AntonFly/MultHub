package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subs", schema = "public", catalog = "multhub")
@IdClass(SubsEntityPK.class)
public class SubsEntity {
    private String login;
    private int projectid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubsEntity that = (SubsEntity) o;
        return projectid == that.projectid &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, projectid);
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
