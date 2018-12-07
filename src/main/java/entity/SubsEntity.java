package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subs", schema = "public", catalog = "multhub")
@IdClass(SubsEntityPK.class)
public class SubsEntity {
    private String login;
    private String projectid;

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
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
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

}
