package ru.lanit.hospital.util;

import lombok.ToString;
import org.springframework.ui.ModelMap;
import ru.lanit.hospital.model.Doctor;

import java.util.List;

@ToString
public class DoctorInfo {

    private int count;
    private List<Doctor> doctors;
    private String message;

    public DoctorInfo(String message, List<Doctor> doctors) {
        this.setMessage(message);
        this.setDoctors(doctors);
    }

    public DoctorInfo(String message, int count) {
        this.setMessage(message);
        this.setCount(count);
    }


    public DoctorInfo() {
    }

    ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
