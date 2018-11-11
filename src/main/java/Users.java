import javax.persistence.*;

@Entity
@Table (name= "users")
public class Users {

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Id
    @JoinColumn(name="connectiondataid")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private String login;
    @Column (name="name")
    private String name;
    @Column (name = "surname")
    private String surname;

    public Users() {
    }

    public Users(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
    }
}
