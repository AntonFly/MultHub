import javax.persistence.*;

@Entity
@Table(name ="connectiondata")
public class ConnectionData {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login=(login);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobilenumb() {
        return mobilenumb;
    }

    public void setMobilenumb(int mobilenumb) {
        this.mobilenumb = mobilenumb;
    }

    @Id
    private String login;
    @Column(name = "E-mail")
    private String email;
    @Column(name="mobilenumb")
    private int mobilenumb;


    public ConnectionData(String login, String email, int mobilenumb) {
        this.login = login;
        this.email = email;
        this.mobilenumb = mobilenumb;
    }

    public ConnectionData() {
    }
}

