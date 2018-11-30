package ru.lanit.hospital.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.hospital.model.Doctor;
import ru.lanit.hospital.services.DoctorService;
import ru.lanit.hospital.util.DoctorInfo;

import java.util.List;

@RestController
@RequestMapping("/doctor/*")
public class DoctorSearchController {

    final static Logger logger = LoggerFactory.getLogger(DoctorSearchController.class);


    private DoctorService docService;

    @Autowired
    public DoctorSearchController (DoctorService doctorService){
        this.docService =doctorService;
    }


/*
    @GetMapping(value = "/count", produces ={"application/json"} )
    public String getDoctorCount(){
        int result = docService.findCount();
        return ("Number of registered doctors: " + result);
    }*/

/*    @GetMapping(value = "/all", produces = {"application/json"} )
    public ModelAndView findAll (DoctorInfo doctorInfo)  {
        return new ModelAndView("findAll",
                new ModelMap("doctors", docService.findAll()));

        }*/

    @GetMapping(value = "/all", produces = {"application/json"} )
    public DoctorInfo findAll ()  {
        int result = 0;
        List<Doctor> all = docService.findAll();

        if(all==null){
            return new DoctorInfo("Number of doctors ", result);
        }
        result = all.size();
        return new DoctorInfo("Number of doctors " + result, result );
       }


    @GetMapping(value = "/{code}", produces = {"application/json"})
    public DoctorInfo getBySpecialityCode(@PathVariable String code){

            List<Doctor> bySpeciality = docService.findBySpeciality(code);
            if(bySpeciality==null){
                return new DoctorInfo("No Doctors found in system",null);
            }
            return new DoctorInfo("Doctors with speciality "+code, bySpeciality );

    }




}
