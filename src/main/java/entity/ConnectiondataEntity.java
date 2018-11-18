package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "connectiondata", schema = "public", catalog = "multhub")
public class ConnectiondataEntity {
    private String login;
    private String eMail;
    private Integer mobilenumb;

    @Id
    @Column(name = "login", nullable = false, length = 30)
    @PrimaryKeyJoinColumn
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "E-mail", nullable = false, length = -1)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "mobilenumb", nullable = true)
    public Integer getMobilenumb() {
        return mobilenumb;
    }

    public void setMobilenumb(Integer mobilenumb) {
        this.mobilenumb = mobilenumb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectiondataEntity that = (ConnectiondataEntity) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(eMail, that.eMail) &&
                Objects.equals(mobilenumb, that.mobilenumb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, eMail, mobilenumb);
    }

}
