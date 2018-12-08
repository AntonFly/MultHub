package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommitsfileEntityPK implements Serializable {
    private String filename;
    private String commitid;

    @Column(name = "filename", nullable = false, length = -1)
    @Id
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name = "commitid", nullable = false, length = -1)
    @Id
    public String getCommitid() {
        return commitid;
    }

    public void setCommitid(String commitid) {
        this.commitid = commitid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitsfileEntityPK that = (CommitsfileEntityPK) o;
        return Objects.equals(filename, that.filename) &&
                Objects.equals(commitid, that.commitid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, commitid);
    }
}
