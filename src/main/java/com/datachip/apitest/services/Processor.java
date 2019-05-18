package com.datachip.apitest.services;

import com.datachip.apitest.Repositories.RegistrationDetailsRepo;
import com.datachip.apitest.entities.RegistrationDetails;
import org.hibernate.mapping.Collection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class Processor {
    @Autowired
    RegistrationDetailsRepo registrationDetailsRepo;

    public JSONObject processregistration(String requestbody) {
        JSONObject jsonObject = new JSONObject();

        try {
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(requestbody);
            JSONObject jsonObject1 = (JSONObject) object;

            String name = (String) jsonObject1.get("name");
            String gender = (String) jsonObject1.get("gender");
            String phonenumber = (String) jsonObject1.get("phonenumber");
            String email = (String) jsonObject1.get("email");
            String password = (String) jsonObject1.get("password");

            RegistrationDetails registrationDetails = new RegistrationDetails();
            registrationDetails.setName(name);
            registrationDetails.setGender(gender);
            registrationDetails.setPhonenumber(phonenumber);
            registrationDetails.setEmail(email);
            registrationDetails.setPassword(password);

            registrationDetailsRepo.save(registrationDetails);

            jsonObject.put("ResponseCode", 0);
            jsonObject.put("ResponseDescription", "User has been successfully registered");

        } catch (Exception ex) {
            jsonObject.put("ResponseCode", 1);
            jsonObject.put("ResponseDescription", "User registration failed");
        }
        return jsonObject;
    }

    public JSONObject processlogin(String incomingstring) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(incomingstring);
            JSONObject jsonObject1 = (JSONObject) object;

            String email = (String) jsonObject1.get("name");
            String password = (String) jsonObject1.get("password");

            RegistrationDetails registrationDetails = new RegistrationDetails();

            ArrayList<RegistrationDetails> registrationDetailsArrayList = new ArrayList<>();
            registrationDetailsArrayList = registrationDetailsRepo.findByEmailAndPassword(email, password);


            //registrationDetailsRepo.findAll().stream().filter(t -> t.getEmail().equals(email) && t.getPassword().equals(password)).forEach(registrationDetailsArrayList::add);

            registrationDetails.setEmail(email);
            registrationDetails.setPassword(password);

            jsonObject.put("Name", registrationDetailsArrayList.get(1).getName());
            jsonObject.put("Email", registrationDetailsArrayList.get(1).getEmail());

            jsonObject.put("ResponseCode", 0);
            jsonObject.put("ResponseDescription", "Login succesful");

        } catch (Exception ex) {

            System.out.println(System.err);
            jsonObject.put("ResponseCode", 1);
            jsonObject.put("ResponseDescription", "Login failed");
        }
        return jsonObject;
    }

}
