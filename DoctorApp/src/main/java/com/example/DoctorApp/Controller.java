package com.example.DoctorApp;

import com.example.DoctorApp.objects.Department;
import com.example.DoctorApp.objects.Doctor;
import com.example.DoctorApp.objects.Patient;
import com.example.DoctorApp.objects.ReasonForVisit;
import com.example.DoctorApp.repos.DoctorRepo;
import com.example.DoctorApp.repos.PatientRepo;
import com.example.DoctorApp.repos.ReasonForVistRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
public class Controller {
    @Autowired
    private ReasonForVistRepo reasonForVisitRepo;
    Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private PatientRepo PatientRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @CrossOrigin
    @GetMapping(path = "/reasons")
    public @ResponseBody
    Iterable<ReasonForVisit> getAllReasonsForVisit() {
        logger.info("Sending Reasons For Visit");
        return reasonForVisitRepo.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/doctor/{reason}")
    public  String getDoctor(@PathVariable String reason){

        ReasonForVisit reasonForVisit = reasonForVisitRepo.findById(reason).get();
        Department reasonDepartment = reasonForVisit.getDepartment();
        List<Doctor> docsInDepartment = doctorRepo.findByDepartment(reasonDepartment);



        return returnedDoctor
    }
    @CrossOrigin

    @PostMapping(path = "/patient")
    public void addNewUser(@RequestBody Map<String,Object> request) throws SQLException{
        logger.info(request.toString());

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);


        String firstname = request.get("firstname").toString();
        String lastname = request.get("lastname").toString();
        String sex = request.get("sex").toString();
        String email = request.get("email").toString();
        String phone = request.get("phone").toString();
        Date birthday = null;
        try {
            birthday = formatter.parse(request.get("birthday").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Patient u = new Patient();

        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setBirthday(birthday);
        u.setEmail(email);
        u.setPhone(phone);
        u.setSex(sex);

        Optional<ReasonForVisit> a =reasonForVisitRepo.findById(request.get("reasonForVisit").toString());
        u.setReasonForVisit(a.get());
      //  System.out.println(u.toString());
      PatientRepo.save(u);

    }

    

}