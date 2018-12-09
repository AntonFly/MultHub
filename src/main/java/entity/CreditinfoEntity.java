package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "creditinfo", schema = "public", catalog = "multhub")
public class CreditinfoEntity {
    private String projectid;
    private Integer cardnumber;
    private Integer qiwimobilephone;
    private Long yamoney;

    @Id
    @Column(name = "progectid", nullable = false, length = 30)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "cardnumber", nullable = true)
    public Integer getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
    }

    @Basic
    @Column(name = "qiwimobilephone", nullable = true)
    public Integer getQiwimobilephone() {
        return qiwimobilephone;
    }

    public void setQiwimobilephone(Integer qiwimobilephone) {
        this.qiwimobilephone = qiwimobilephone;
    }

    @Basic
    @Column(name = "yamoney", nullable = true)
    public Long getYamoney() {
        return yamoney;
    }

    public void setYamoney(Long yamoney) {
        this.yamoney = yamoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditinfoEntity that = (CreditinfoEntity) o;
        return Objects.equals(projectid, that.projectid) &&
                Objects.equals(cardnumber, that.cardnumber) &&
                Objects.equals(qiwimobilephone, that.qiwimobilephone) &&
                Objects.equals(yamoney, that.yamoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectid, cardnumber, qiwimobilephone, yamoney);
    }

}
