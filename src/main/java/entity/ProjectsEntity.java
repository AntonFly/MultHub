package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "projects", schema = "public", catalog = "multhub")
public class ProjectsEntity {
    private String projectid;
    private String name;
    private String description;
    private Integer curbudget;
    private Integer goalbudget;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectid", nullable = false)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
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
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "curbudget", nullable = true)
    public Integer getCurbudget() {
        return curbudget;
    }

    public void setCurbudget(Integer curbudget) {
        this.curbudget = curbudget;
    }

    @Basic
    @Column(name = "goalbudget", nullable = true)
    public Integer getGoalbudget() {
        return goalbudget;
    }

    public void setGoalbudget(Integer goalbudget) {
        this.goalbudget = goalbudget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectsEntity that = (ProjectsEntity) o;
        return projectid == that.projectid &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(curbudget, that.curbudget) &&
                Objects.equals(goalbudget, that.goalbudget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectid, name, description, curbudget, goalbudget);
    }
}
