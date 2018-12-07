package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "followers", schema = "public", catalog = "multhub")
@IdClass(FollowersEntityPK.class)
public class FollowersEntity {
    private String login;
    private String follower;

    @Id
    @Column(name = "login", nullable = false, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    @Column(name = "follower", nullable = false, length = -1)
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
        FollowersEntity that = (FollowersEntity) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(follower, that.follower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, follower);
    }
}
