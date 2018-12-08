package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commitsfile", schema = "public", catalog = "multhub")
@IdClass(CommitsfileEntityPK.class)
public class CommitsfileEntity {
    private String filename;
    private Integer filepath;
    private String commitid;

    @Id
    @Column(name = "filename", nullable = false, length = -1)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "filepath", nullable = true)
    public Integer getFilepath() {
        return filepath;
    }

    public void setFilepath(Integer filepath) {
        this.filepath = filepath;
    }

    @Id
    @Column(name = "commitid", nullable = false, length = -1)
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
        CommitsfileEntity that = (CommitsfileEntity) o;
        return Objects.equals(filename, that.filename) &&
                Objects.equals(filepath, that.filepath) &&
                Objects.equals(commitid, that.commitid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, filepath, commitid);
    }
}
