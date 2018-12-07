package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FollowersEntityPK implements Serializable {
    private String login;
    private String follower;

    @Column(name = "login", nullable = false, length = -1)
    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "follower", nullable = false, length = -1)
    @Id
    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowersEntityPK that = (FollowersEntityPK) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(follower, that.follower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, follower);
    }
}
