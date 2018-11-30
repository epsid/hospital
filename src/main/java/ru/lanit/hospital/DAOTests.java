package ru.lanit.hospital;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lanit.hospital.configuration.AppConfig;
import ru.lanit.hospital.repositories.DoctorDAO;
import ru.lanit.hospital.repositories.RxDAO;
import ru.lanit.hospital.repositories.UserDAO;



public class DAOTests {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DoctorDAO dao = ctx.getBean("doctorDAO", DoctorDAO.class);
        UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
        RxDAO rxDAO = ctx.getBean("rxDAO", RxDAO.class);

        //TODO Paste your users data here, to test DAO's methods.
        String speciality = "Default";
        int docId = 25;
        int patientId = 26;
        String docEmail = "doc@email.com";
        String docPassword = "password";
        String patientEmail = "patient@email.com";
        String patPassword = "password";


        try {
            //DOCTOR
            System.out.println("_______________________________________COUNT: " + dao.findCount());
            System.out.println("_______________________________________All: " + dao.findAll());
            System.out.println("_______________________________________BY SPECIALITY: " + dao.findBySpeciality(speciality));
            System.out.println("_______________________________________BY ID: " + dao.findByUserId(docId));
            //USER
            System.out.println("_______________________________________BY EMAIL: " + userDAO.getByEmail(docEmail));
            System.out.println("_______________________________________VALID USER(DOCTOR): " + userDAO.isValidUser(docEmail, docPassword));
            System.out.println("_______________________________________VALID USER(PATIENT): " + userDAO.isValidUser(patientEmail, patPassword));
            System.out.println("_______________________________________USER BY ID: " + userDAO.getById(patientId));
            //RX
            System.out.println("_______________________________________FIND RX BY DOCTOR ID: " + rxDAO.findByDoctorId(docId));
            System.out.println("_______________________________________FIND RX BY PATIENT ID: " + rxDAO.findByPatientId(patientId));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
