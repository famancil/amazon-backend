package com.java.biblioteca.demo.controllers;

import com.java.biblioteca.demo.entities.User;
import com.java.biblioteca.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    /*@Autowired
    private BCryptPasswordEncoder passwordEncoder;*/

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {

        List<User> users = null;
        Map<String, Object> response = new HashMap<>();

        try {
            users = userService.findAll();

        } catch (DataAccessException e) {
            response.put("keyError", "USER-CATCH-ERROR-FIND-ALL");
            response.put("payload", e.getMessage().concat(": ").concat( e.getMostSpecificCause().getMessage()));

            //logger.error("USER-CATCH-ERROR-FIND-ALL: ", e.getMessage().concat(": ").concat( e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        response.put("data", users );

        return new ResponseEntity<Map<String, Object>>( response, HttpStatus.OK );
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> adduser(@Valid @RequestBody User newUser, BindingResult result){

        User user = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream().map(err ->  err.getField() + ": " + err.getDefaultMessage()).collect( Collectors.toList());

            response.put("keyError", "USER-FORM-ERROR-ADD");
            response.put("payload", errors );

            //logger.error("USER-FORM-ERROR-ADD: ", errors);

            return new ResponseEntity<Map<String, Object>>( response, HttpStatus.BAD_REQUEST );
        }

        try {
            newUser.prePersist();
            //newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user = userService.save( newUser );

        } catch (DataAccessException e){
            response.put("keyError", "USER-CATCH-ERROR-ADD");
            response.put("payload", e.getMessage().concat(": ").concat( e.getMostSpecificCause().getMessage()));

            //logger.error("USER-CATCH-ERROR-ADD: ", e.getMessage().concat(": ").concat( e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        response.put("key", "USER-ADDED");
        response.put("data", user );

        return new ResponseEntity<Map<String, Object>>( response, HttpStatus.CREATED );
    }



}
