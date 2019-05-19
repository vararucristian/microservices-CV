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

@RestController
@RequestMapping("/profile")
public class PreferencesCollector {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private Boolean verifyId(int id) {
        Boolean exists = true;
        String verifyIdStatement = "SELECT count(id_user) from preferences where id_user = " + id;
        int result = jdbcTemplate.queryForObject(verifyIdStatement, Integer.class);
        if (result == 0)
            exists = false;
        return exists;
    }

    private Boolean verifyId2(int id) {
        Boolean exists = true;
        String verifyIdStatement = "SELECT count(id) from MS_USER where id = " + id;
        int result = jdbcTemplate.queryForObject(verifyIdStatement, Integer.class);
        if (result == 0)
            exists = false;
        return exists;
    }

    private Boolean verifyTime(String time) {
        Boolean exists = true;
        if (time.equals("morning") == false && time.equals("afternoon") == false && time.equals("evening") == false)
            exists = false;
        return exists;
    }

    private Boolean verifyCamp(String camp) {
        Boolean exists = true;
        if (camp.equals("nume") == false && camp.equals("prenume") == false && camp.equals("email") == false
                && camp.equals("parola") == false && camp.equals("data_inregistrare") == false)
            exists = false;
        return exists;
    }

    private Boolean verifyPref(String pref) {
        Boolean exists = true;
        if (pref.equals("relaxed") == false && pref.equals("tired") == false && pref.equals("focused") == false)
            exists = false;
        return exists;
    }

    @RequestMapping("/edit-preferences/{accId}/{time}/{pref}")
    private ResponseEntity<String> editPreferences(@PathVariable("accId") int accId, @PathVariable("time") String time, @PathVariable("pref") String pref) {
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(accId) == false) {
            Response response = new Response(0, "The profile with id " + accId + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);

        }
        else if (verifyTime(time) == false) {
            Response response = new Response(0, "The time of the day " + time + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else if (verifyPref(pref) == false) {
            Response response = new Response(0, "The preference " + pref + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else {
            String interogare = "select " + time + " from preferences where id_user = " + accId;
            String timp = jdbcTemplate.queryForObject(interogare, String.class);
            if (timp.equals(pref) == true) {
                Response response = new Response(0, "The preference of user with the id " + accId + " is already set to " + pref + " for " + time);
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.CONFLICT);
            }
            else {
                String editPref = "update preferences set " + time + " = " + "\"" + pref + "\"" + " where id_user = " + accId;
                jdbcTemplate.execute(editPref);
                Response response = new Response(1, "Succes.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
            }
        }
    }

    @RequestMapping("/get-preferences/{userID}")
    public ResponseEntity<String> getPreferences(@PathVariable("userID") int userID){
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(userID) == false) {
            Response response = new Response(0, "The profile with id " + userID + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        } else {
            String selectPreferences = "select count(id_user) from preferences where id_user = " + userID;
            Boolean prefExists = jdbcTemplate.queryForObject(selectPreferences, Boolean.class);
            if (prefExists == false) {
                Response response =
                        new Response(0, "The profile with id " + userID + " has not preferences");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.BAD_REQUEST);
            } else {
                String selectPref = "select morning, afternoon, evening from preferences where id_user = " + userID;
                RowMapper<Preferences> rowMapper = new BeanPropertyRowMapper<Preferences>(Preferences.class);
                Response response = new Response(1, "success");
                Response1 response1 =
                        new Response1(response, this.jdbcTemplate.query(selectPref, rowMapper));
                return new ResponseEntity<String>(response1.toString(), responseHeaders, HttpStatus.OK);
            }
        }
    }

    @RequestMapping("/delete-preferences/{userID}")
    public ResponseEntity<String> deletePreferences(@PathVariable("userID") int userID){
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(userID) == false) {
            Response response = new Response(0, "The profile with id " + userID + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }
        else {
            String deletePref = "delete from preferences where id_user = " + userID;
            jdbcTemplate.execute(deletePref);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
        }
    }

    @RequestMapping("/add-preferences/{userID}/{op1}/{op2}/{op3}")
    public ResponseEntity<String> addPreferences(@PathVariable("userID") int userID,@PathVariable("op1") String op1,@PathVariable("op2") String op2,@PathVariable("op3") String op3) {
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");

        if (verifyId(userID) != false)
        {
            Response response = new Response(0, "The profile with id " + userID + " already exists.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else if (verifyPref(op1) == false)
        {
            Response response = new Response(0, "The preference " + op1 + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else if (verifyPref(op2) == false)
        {
            Response response = new Response(0, "The preference " + op2 + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else if (verifyPref(op3) == false)
        {
            Response response = new Response(0, "The preference " + op3 + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else
        {
            String insertIntoPreferences = "insert into preferences values (" + userID + ", '" + op1 + "','" + op2 + "','" + op3 + "');";
            jdbcTemplate.execute(insertIntoPreferences);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
        }
    }


    @RequestMapping("/get-profile/{userID}")
    public ResponseEntity<String> getProfile(@PathVariable("userID") int userID) {
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId2(userID) == false) {
            Response response = new Response(0, "The profile with id " + userID + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        } else {
            String selectPreferences = "select count(id) from MS_USER where id = " + userID;
            Boolean prefExists = jdbcTemplate.queryForObject(selectPreferences, Boolean.class);
            if (prefExists == false) {
                Response response =
                        new Response(0, "The profile with id " + userID + " has not preferences");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.BAD_REQUEST);
            } else {
                String selectPref = "select nume, prenume, email,parola,data_inregistrare from MS_USER where id = " + userID;
                RowMapper<Profile> rowMapper = new BeanPropertyRowMapper<Profile>(Profile.class);
                Response response = new Response(1, "success");
                Response2 response1 =
                        new Response2(response, this.jdbcTemplate.query(selectPref, rowMapper));
                return new ResponseEntity<String>(response1.toString(), responseHeaders, HttpStatus.OK);
            }
        }
    }

    @RequestMapping("/delete-profile/{userID}")
    public ResponseEntity<String> deleteProfile(@PathVariable("userID") int userID) {
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId2(userID) == false) {
            Response response = new Response(0, "The profile with id " + userID + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }
        else {
            String deletePref = "delete from MS_USER where id = " + userID;
            jdbcTemplate.execute(deletePref);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
        }
    }

    @RequestMapping("/edit-profile/{accId}/{camp}/{pref}")
    private ResponseEntity<String> editProfile(@PathVariable("accId") int accId, @PathVariable("camp") String camp, @PathVariable("pref") String pref) {
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId2(accId) == false) {
            Response response = new Response(0, "The profile with id " + accId + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);

        }
        else if (verifyCamp(camp) == false) {
            Response response = new Response(0, "The field " + camp + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else {
            String interogare = "select " + camp + " from MS_USER where id = " + accId;
            String timp = jdbcTemplate.queryForObject(interogare, String.class);
            if (timp.equals(pref) == true) {
                Response response = new Response(0, "The field of user with the id " + accId +
                        " is already set to " + pref + " for " + camp);
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.CONFLICT);
            }
            else {
                String editPref = "update MS_USER set " + camp + " = " + "\"" + pref + "\"" + " where id = " + accId;
                jdbcTemplate.execute(editPref);
                Response response = new Response(1, "Succes.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
            }
        }
    }

    @RequestMapping("/add-profile/{userID}/{op1}/{op2}/{op3}/{op4}/{op5}")
    public ResponseEntity<String> addProfile(@PathVariable("userID") int userID,@PathVariable("op1") String op1,@PathVariable("op2") String op2,
                                             @PathVariable("op3") String op3,@PathVariable("op4") String op4,@PathVariable("op5") String op5) {
        URI location = null;
        try
        {
            location = new URI("localhost");
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");

        if (verifyId2(userID) != false)
        {
            Response response = new Response(0, "The profile with id " + userID + " already exists.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }

        else
        {
            String insertIntoPreferences = "insert into MS_USER values (" + userID + ", '" + op1 + "','" + op2 + "','" + op3 + "','" + op4 + "','" + op5 + "');";
            jdbcTemplate.execute(insertIntoPreferences);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
        }
    }
}
