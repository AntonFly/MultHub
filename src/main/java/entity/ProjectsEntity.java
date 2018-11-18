package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "projects", schema = "public", catalog = "multhub")
public class ProjectsEntity {
    private int projectid;
    private String name;
    private String description;
    private Serializable curbudget;
    private Serializable goalbudget;

    @Id
    @Column(name = "projectid", nullable = false)
    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
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
    public Serializable getCurbudget() {
        return curbudget;
    }

    public void setCurbudget(Serializable curbudget) {
        this.curbudget = curbudget;
    }

    @Basic
    @Column(name = "goalbudget", nullable = true)
    public Serializable getGoalbudget() {
        return goalbudget;
    }

    public void setGoalbudget(Serializable goalbudget) {
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
