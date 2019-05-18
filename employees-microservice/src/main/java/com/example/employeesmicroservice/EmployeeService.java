package com.example.employeesmicroservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Boolean verifyId(int id) {
        Boolean exists = true;
        String verifyIdStatement = "SELECT count(id) from employee where id = " + id;
        int result = jdbcTemplate.queryForObject(verifyIdStatement, Integer.class);
        if (result == 0)
            exists = false;
        return exists;
    }

    public Response verifyPosition(int idEmployee) {
        if (!verifyId(idEmployee)) {
            Response response = new Response(0, "The id "
                    + idEmployee + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        } else {
            String selectPositionStatement = "SELECT position from employee where id = " + idEmployee;
            String position = jdbcTemplate.queryForObject(selectPositionStatement, String.class);
            if (!position.equals("human resources")) {
                Response response = new Response(0, "The employee with  id "
                        + idEmployee + " is not allowed to make changes.", HttpStatus.METHOD_NOT_ALLOWED);
                return response;
            } else {
                Response response = new Response(1, "Succes", HttpStatus.OK);
                return response;
            }

        }
    }

    public Response addUnderling(int idUnderling, int idSuperior, String position) {
        // verificare pozitie valida?
        if (!verifyId(idUnderling)) {
            if (!verifyId(idSuperior)) {
                Response response = new Response(0, "The superior with id " + idSuperior
                        + " does not exist.", HttpStatus.NOT_FOUND);
                return response;
            } else {
                String insertIntoEmployeeStatement = "insert into employee (ID, position) " +
                        "values (" + idUnderling + ", '" + position + "');";
                jdbcTemplate.execute(insertIntoEmployeeStatement);
                String insertIntoUnderlingsStatement = "insert into underlings (id_superior, id_underling)" +
                        " values (" + idSuperior + ", " + idUnderling + ");";
                jdbcTemplate.execute(insertIntoUnderlingsStatement);
                Response response = new Response(1, "Succes", HttpStatus.OK);
                return response;
            }
        } else {
            Response response = new Response(0, "This employee id already exists.", HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    public Response changeSuperior(int idUnderling, int idSuperior) {
        if (!verifyId(idUnderling)) {
            Response response = new Response(0, "The employee with id " +
                    idUnderling + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        } else if (!verifyId(idSuperior)) {
            Response response = new Response(0, "The superior with id " +
                    idSuperior + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        } else {
            String deleteStatement = "delete from underlings where id_underling=" + idUnderling + ";";
            jdbcTemplate.execute(deleteStatement);
            String insertStatement = "insert into underlings (id_superior, id_underling)" +
                    " values (" + idSuperior + ", " + idUnderling + ");";
            jdbcTemplate.execute(insertStatement);
            Response response = new Response(1, "Succes", HttpStatus.OK);
            return response;
        }
    }

    public Response removeUnderling(int idUnderling) {
        if (!verifyId(idUnderling)) {
            Response response = new Response(0, "The employee with id "
                    + idUnderling + " does not exist.", HttpStatus.NOT_FOUND);
            return response;
        } else {
            String deleteFromUnderlingsStatement = "delete from underlings where id_underling=" + idUnderling + ";";
            jdbcTemplate.execute(deleteFromUnderlingsStatement);
            String deleteFromUnderlingsSuperiorStatement = "delete from underlings where id_superior=" + idUnderling + ";";
            jdbcTemplate.execute(deleteFromUnderlingsSuperiorStatement);
            String deleteFromEmployeeStatement = "delete from employee where ID=" + idUnderling + ";";
            jdbcTemplate.execute(deleteFromEmployeeStatement);
            Response response = new Response(1, "Succes.", HttpStatus.OK);
            return response;
        }
    }

    public Response1 getProfileUnderlings(List<Underling> underlings){
        List <UnderlingData> myUnderlings= new ArrayList<UnderlingData>();
        for (Underling underling: underlings) {
            ResponseEntity<String> raspuns = null;
            String url = "http://104.199.20.255:8100/profile/get-profile/" + underling.getID();
            try{
                raspuns=new RestTemplate().getForEntity(url, String.class);
            }catch(HttpClientErrorException e){
                Response response = new Response(0, "The underling with id "
                        + underling.getID() + " do not have a profile. ", HttpStatus.NOT_FOUND);
                Response1 response1 = new Response1(response, null);
                return response1;
            }
            try {
                JSONArray obj = new JSONArray(raspuns.toString().substring(5));
                JSONArray obj2 = obj.getJSONArray(1);
                JSONObject obj1  = obj.optJSONObject(0);
                JSONObject profile = obj2.optJSONObject(0);
                if (profile != null){
                    String email = profile.getString("email");
                    myUnderlings.add(new UnderlingData(underling, email));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }}
        Response response = new Response(1, "success", HttpStatus.OK);
        Response1 response1 = new Response1(response, myUnderlings);
        return response1;
    }

    public Response1 viewUnderlings(int userID)  {
        if (!verifyId(userID)) {
            Response response = new Response(0, "The employee with id "
                    + userID + " does not exist.", HttpStatus.NOT_FOUND);
            Response1 response1 = new Response1(response, null);
            return response1;
        } else {
            String selectNumberUnderlings = "select count(id_superior) from underlings where id_superior = " + userID;
            int numberUnderlings = jdbcTemplate.queryForObject(selectNumberUnderlings, Integer.class);
            if (numberUnderlings == 0) {
                Response response = new Response(0, "The employee with id "
                        + userID + " has not underlings", HttpStatus.BAD_REQUEST);
                Response1 response1 = new Response1(response, null);
                return response1;
            } else {
                String selectUnderlings = "select ID, position from employee " +
                        " join underlings on ID = id_underling where id_superior = " + userID;
                RowMapper<Underling> rowMapper = new BeanPropertyRowMapper<Underling>(Underling.class);
                List<Underling> underlings =  this.jdbcTemplate.query(selectUnderlings, rowMapper);
                return getProfileUnderlings(underlings);
                }
            }
        }
    public ResponsePosition getPosition(int userID)  {
        if (!verifyId(userID)) {
            Response response = new Response(0, "The employee with id "
                    + userID + " does not exist.", HttpStatus.NOT_FOUND);
            ResponsePosition responsePosition = new ResponsePosition(response, null);
            return responsePosition;
        } else {
                String selectPosition = "select  position from employee where ID= "+ userID;
                String position = jdbcTemplate.queryForObject(selectPosition, String.class) ;
                Response response = new Response(1, "Succes.", HttpStatus.OK);
                ResponsePosition responsePosition = new ResponsePosition(response, position);
                return responsePosition;
            }
        }

    public EmployeeResponse getEmployees(){
        String select = "select ID from employee";
        List<Integer> ids =  jdbcTemplate.queryForList(select, Integer.class);
        Response response = new Response(1, "success", HttpStatus.OK);
        EmployeeResponse responseEmployee = new EmployeeResponse(response, ids);
        return responseEmployee;
    }
}


