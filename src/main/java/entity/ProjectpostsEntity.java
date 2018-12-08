package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "projectposts", schema = "public", catalog = "multhub")
public class ProjectpostsEntity {
    private String id;
    private String projectid;
    private String text;
    private Timestamp time;
    private String filepath;

    @Id
    @Column(name = "id", nullable = false, length = -1)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "projectid", nullable = true, length = -1)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "text", nullable = true, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "filepath", nullable = true, length = -1)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectpostsEntity that = (ProjectpostsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(text, that.text) &&
                Objects.equals(time, that.time) &&
                Objects.equals(filepath, that.filepath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectid, text, time, filepath);
    }
}
