package com.java.biblioteca.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String holamundo(){
        return "HOLA MUNDO";
    }
}
