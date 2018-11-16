package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RequestsEntityPK implements Serializable {
    private String login;
    private int projectid;

    @Column(name = "login", nullable = false, length = 30)
    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "projectid", nullable = false)
    @Id
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
        RequestsEntityPK that = (RequestsEntityPK) o;
        return projectid == that.projectid &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, projectid);
    }
}