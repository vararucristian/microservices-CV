package com.example.preferencesmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class PreferencesService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Boolean verifyId(int id) {
        Boolean exists = true;
        String verifyIdStatement = "SELECT count(id_user) from preferences where id_user = " + id;
        int result = jdbcTemplate.queryForObject(verifyIdStatement, Integer.class);
        if (result == 0)
            exists = false;
        return exists;
    }

    public Boolean verifyTime(String time) {
        Boolean exists = true;
        if (time.equals("morning") == false && time.equals("afternoon") == false && time.equals("evening") == false)
            exists = false;
        return exists;
    }

    public Boolean verifyPref(String pref) {
        Boolean exists = true;
        if (pref.equals("relaxed") == false && pref.equals("tired") == false && pref.equals("focused") == false)
            exists = false;
        return exists;
    }

    public Response editPreferences(int accId, String time, String pref){
        if (verifyId(accId) == false) {
            Response response = new Response(0, "The profile with id "
                    + accId + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else if (verifyTime(time) == false) {
            Response response = new Response(0, "The time of the day "
                    + time + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else if (verifyPref(pref) == false) {
            Response response = new Response(0, "The preference "
                    + pref + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else {
            String interogare = "select " + time + " from preferences where id_user = " + accId;
            String timp = jdbcTemplate.queryForObject(interogare, String.class);
            if (timp.equals(pref) == true) {
                Response response = new Response(0, "The preference of user with the id "
                        + accId + " is already set to " + pref + " for " + time, HttpStatus.CONFLICT);
                return response;
            }
            else {
                String editPref = "update preferences set " + time + " = " + "\"" + pref + "\"" + " where id_user = " + accId;
                jdbcTemplate.execute(editPref);
                Response response = new Response(1, "Succes.", HttpStatus.OK);
                return response;
            }
        }
    }

    public Response1 getPreferences(int userID) {
        if (verifyId(userID) == false) {
            Response response = new Response(0, "The profile with id "
                    + userID + " does not exist.", HttpStatus.NOT_FOUND);
            return new Response1(response, null);
        } else {
            String selectPreferences = "select count(id_user) from preferences where id_user = " + userID;
            Boolean prefExists = jdbcTemplate.queryForObject(selectPreferences, Boolean.class);
            if (prefExists == false) {
                Response response = new Response(0, "The profile with id "
                        + userID + " has not preferences", HttpStatus.BAD_REQUEST);
                return new Response1(response, null);
            } else {
                String selectPref = "select morning, afternoon, evening from preferences where id_user = " + userID;
                RowMapper<Preferences> rowMapper = new BeanPropertyRowMapper<Preferences>(Preferences.class);
                Response response = new Response(1, "success", HttpStatus.OK);
                Response1 response1 =
                        new Response1(response, this.jdbcTemplate.query(selectPref, rowMapper));
                return response1;
            }
        }
    }

    public Response deletePreferences(int userID) {
        if (verifyId(userID) == false) {
            Response response = new Response(0, "The profile with id "
                    + userID + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else {
            String deletePref = "delete from preferences where id_user = " + userID;
            jdbcTemplate.execute(deletePref);
            Response response = new Response(1, "Succes.", HttpStatus.OK);
            return response;
        }
    }

    public Response addPreferences(int userID, String op1, String op2, String op3) {
        if (verifyPref(op1) == false)
        {
            Response response = new Response(0, "The preference "
                    + op1 + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else if (verifyPref(op2) == false)
        {
            Response response = new Response(0, "The preference "
                    + op2 + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else if (verifyPref(op3) == false)
        {
            Response response = new Response(0, "The preference "
                    + op3 + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        }
        else
        {
            String insertIntoPreferences = "insert into preferences values (" + userID
                    + ", '" + op1 + "','" + op2 + "','" + op3 + "');";
            jdbcTemplate.execute(insertIntoPreferences);
            Response response = new Response(1, "Succes.", HttpStatus.OK);
            return response;
        }
    }
}
