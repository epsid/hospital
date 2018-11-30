package ru.lanit.hospital.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@NoArgsConstructor
@Entity
@Table(name = "doctor", schema = "hospital")

@NamedQueries({
        @NamedQuery(name = "findBySpeciality", query = "FROM Doctor doc WHERE doc.specialityCode=:specialityCode"),
        @NamedQuery(name = "findAll", query = "FROM Doctor"),
        @NamedQuery(name = "findByUserEmailAddress", query = "FROM User usr WHERE usr.email=:email"),
        @NamedQuery(name = "findCount", query = "SELECT COUNT(*) FROM Doctor"),
        @NamedQuery(name = "findByUserId", query = "FROM Doctor doc WHERE doc.user.id=:userId"),
})
public class Doctor implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String specialityCode;
    private Timestamp createTime;
    private Timestamp lastUpdated;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Basic
    /*@Column(name = "specialityCode")*/
    public String getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }

    @Basic
    /* @Column(name = "create_time")*/
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    /*@Column(name = "last_updated")*/
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id &&
                Objects.equals(specialityCode, doctor.specialityCode) &&
                Objects.equals(createTime, doctor.createTime) &&
                Objects.equals(lastUpdated, doctor.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialityCode, createTime, lastUpdated);
    }
}
