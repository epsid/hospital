package ru.lanit.hospital.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Entity
@Table(name = "rx", schema = "hospital")


@NamedQueries({
        @NamedQuery(name = "findByDoctorId", query = "FROM Rx rx WHERE rx.doctor.user.id =:doctorId"),
        @NamedQuery(name = "findByPatientId", query = "FROM Rx rx WHERE rx.user.id=:userId")


})
public class Rx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String symptoms;
    private String medicine;
    private Timestamp createTime;
    private Timestamp lastUpdated;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "symptoms")
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    @Basic
    @Column(name = "medicine")
    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "lastUpdated")
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rx rx = (Rx) o;
        return id == rx.id &&
                Objects.equals(symptoms, rx.symptoms) &&
                Objects.equals(medicine, rx.medicine) &&
                Objects.equals(createTime, rx.createTime) &&
                Objects.equals(lastUpdated, rx.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symptoms, medicine, createTime, lastUpdated);
    }
}
