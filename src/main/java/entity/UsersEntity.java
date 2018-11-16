package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "multhub")
public class UsersEntity {
    private String login;
    private String name;
    private String surname;
    private Collection<CommentsEntity> commentsByLogin;
    private ConnectiondataEntity connectiondataByLogin;
    private CreditinfoEntity creditinfoByLogin;
    private Collection<DevelopersEntity> developersByLogin;
    private Collection<RequestsEntity> requestsByLogin;
    private Collection<SubsEntity> subsByLogin;

    @Id
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, surname);
    }

    @OneToMany(mappedBy = "usersByLogin")
    public Collection<CommentsEntity> getCommentsByLogin() {
        return commentsByLogin;
    }

    public void setCommentsByLogin(Collection<CommentsEntity> commentsByLogin) {
        this.commentsByLogin = commentsByLogin;
    }

    @OneToOne(mappedBy = "usersByLogin")
    public ConnectiondataEntity getConnectiondataByLogin() {
        return connectiondataByLogin;
    }

    public void setConnectiondataByLogin(ConnectiondataEntity connectiondataByLogin) {
        this.connectiondataByLogin = connectiondataByLogin;
    }

    @OneToOne(mappedBy = "usersByLogin")
    public CreditinfoEntity getCreditinfoByLogin() {
        return creditinfoByLogin;
    }

    public void setCreditinfoByLogin(CreditinfoEntity creditinfoByLogin) {
        this.creditinfoByLogin = creditinfoByLogin;
    }

    @OneToMany(mappedBy = "usersByLogin")
    public Collection<DevelopersEntity> getDevelopersByLogin() {
        return developersByLogin;
    }

    public void setDevelopersByLogin(Collection<DevelopersEntity> developersByLogin) {
        this.developersByLogin = developersByLogin;
    }

    @OneToMany(mappedBy = "usersByLogin")
    public Collection<RequestsEntity> getRequestsByLogin() {
        return requestsByLogin;
    }

    public void setRequestsByLogin(Collection<RequestsEntity> requestsByLogin) {
        this.requestsByLogin = requestsByLogin;
    }

    @OneToMany(mappedBy = "usersByLogin")
    public Collection<SubsEntity> getSubsByLogin() {
        return subsByLogin;
    }

    public void setSubsByLogin(Collection<SubsEntity> subsByLogin) {
        this.subsByLogin = subsByLogin;
    }
}
