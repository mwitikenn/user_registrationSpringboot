package com.datachip.apitest.controller;


import com.datachip.apitest.services.Processor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user" )

public class MainController {
    @Autowired
    Processor processor;
    //method to register
    @RequestMapping(path = "/register" )
    public JSONObject registeruser(@RequestBody String incomingrequest){
        JSONObject jsonObject = new JSONObject();
        try{
            System.out.println("The incoming message is" + incomingrequest);
            jsonObject=processor.processregistration(incomingrequest);
        }
        catch (Exception ex){

        }
        return jsonObject;
    }
    //method to login
    @RequestMapping(path = "/login" )
    public JSONObject loginuser(@RequestBody String incomingrequest){
        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject = processor.processlogin(incomingrequest);
        }
        catch (Exception ex){

        }
        return jsonObject;
    }
}
