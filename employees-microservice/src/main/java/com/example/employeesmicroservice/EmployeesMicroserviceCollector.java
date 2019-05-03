package com.example.employeesmicroservice;
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
@RequestMapping("/employees")
public class EmployeesMicroserviceCollector {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private Boolean verifyId(int id)
    {
        Boolean exists= true;
        String verifyIdStatement = "SELECT count(id) from employee where id = " + id;
        int result =  jdbcTemplate.queryForObject(verifyIdStatement, Integer.class);
        if (result==0)
            exists=false;
        return exists;
    }
    @RequestMapping("/tryGet/{idEmployee}")
    public ResponseEntity<String> verifyPosition(@PathVariable("idEmployee") int idEmployee) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(idEmployee) == false) {
            Response response= new Response(0, "The id " + idEmployee + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }
        else {
            String selectPositionStatement = "SELECT position from employee where id = " + idEmployee;
            String position = jdbcTemplate.queryForObject(selectPositionStatement, String.class);
            if (!position.equals("human resources")) {
                Response response= new Response(0, "The employee with  id " + idEmployee + " is not allowed to make changes.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
            }
            else
            {
                Response response= new Response(1,"Succes");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
            }

        }
    }
}
