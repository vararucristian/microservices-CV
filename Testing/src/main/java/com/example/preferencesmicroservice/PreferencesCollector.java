package com.example.preferencesmicroservice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.net.URI;
import java.net.URISyntaxException;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;

@RestController
@RequestMapping("/preferences")
public class PreferencesCollector {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PreferencesService preferencesService;

    @RequestMapping("/edit-preferences/{accId}/{time}/{pref}")
    private ResponseEntity<String> editPreferences(@PathVariable("accId") int accId, @PathVariable("time") String time, @PathVariable("pref") String pref) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        Response response = preferencesService.editPreferences(accId, time, pref);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }

    @RequestMapping("/get-preferences/{userID}")
    public ResponseEntity<String> getPreferences(@PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        Response1 response1 = preferencesService.getPreferences(userID);
        return new ResponseEntity<String>(response1.toString(), responseHeaders, response1.getResponse().getStatus());
    }

    @RequestMapping("/delete-preferences/{userID}")
    public ResponseEntity<String> deletePreferences(@PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        Response response = preferencesService.deletePreferences(userID);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }
    
    @RequestMapping("/add-preferences/{userID}/{op1}/{op2}/{op3}")
    public ResponseEntity<String> addPreferences(@PathVariable("userID") int userID,@PathVariable("op1") String op1,@PathVariable("op2") String op2,@PathVariable("op3") String op3) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        Response response = preferencesService.addPreferences(userID, op1, op2, op3);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }
}

