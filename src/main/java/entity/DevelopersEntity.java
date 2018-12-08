package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "developers", schema = "public", catalog = "multhub")
@IdClass(DevelopersEntityPK.class)
public class DevelopersEntity {
    private String login;
    private String projectid;
    private Projpos projpos;
    private String description;

    @Id
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    @Column(name = "projectid", nullable = false, length = -1)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "projpos", nullable = true)
    public Projpos getProjpos() {
        return projpos;
    }

    public void setProjpos(Projpos  projpos) {
        this.projpos = projpos;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevelopersEntity that = (DevelopersEntity) o;
        return projectid == that.projectid &&
                Objects.equals(login, that.login) &&
                projpos == that.projpos &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, projectid, projpos, description);
    }

}
