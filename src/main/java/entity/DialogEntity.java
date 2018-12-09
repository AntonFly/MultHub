package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dialog", schema = "public", catalog = "multhub")
public class DialogEntity {
    private String id;
    private String oneUserId;
    private String twoUserId;
    @Id
    @Column(name = "id", nullable = false, length = -1)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "one_user_id", nullable = true, length = -1)
    public String getOneUserId() {
        return oneUserId;
    }

    public void setOneUserId(String oneUserId) {
        this.oneUserId = oneUserId;
    }

    @Basic
    @Column(name = "two_user_id", nullable = true, length = -1)
    public String getTwoUserId() {
        return twoUserId;
    }

    public void setTwoUserId(String twoUserId) {
        this.twoUserId = twoUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogEntity that = (DialogEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(oneUserId, that.oneUserId) &&
                Objects.equals(twoUserId, that.twoUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oneUserId, twoUserId);
    }
}
