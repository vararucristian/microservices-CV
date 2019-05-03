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

    private Boolean verifyId(int id) {
        Boolean exists = true;
        String verifyIdStatement = "SELECT count(id) from employee where id = " + id;
        int result = jdbcTemplate.queryForObject(verifyIdStatement, Integer.class);
        if (result == 0)
            exists = false;
        return exists;
    }

    @RequestMapping("/try-get/{idEmployee}")
    private ResponseEntity<String> verifyPosition(@PathVariable("idEmployee") int idEmployee) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(idEmployee) == false) {
            Response response = new Response(0, "The id " + idEmployee + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        } else {
            String selectPositionStatement = "SELECT position from employee where id = " + idEmployee;
            String position = jdbcTemplate.queryForObject(selectPositionStatement, String.class);
            if (!position.equals("human resources")) {
                Response response = new Response(0, "The employee with  id " + idEmployee + " is not allowed to make changes.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
            } else {
                Response response = new Response(1, "Succes");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);
            }

        }
    }

    @RequestMapping("/add-underling/{idUnderling}/{idSuperior}/{position}")
    private ResponseEntity<String> addUnderling(@PathVariable("idUnderling") int idUnderling, @PathVariable("idSuperior") int idSuperior, @PathVariable("position") String position) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        // verificare pozitie valida?
        if (verifyId(idUnderling) == false) {
            if (verifyId(idSuperior) == false) {
                Response response = new Response(0, "The superior with id " + idSuperior + " does not exist.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
            }
            else
            {
            String insertIntoEmployeeStatement = "insert into employee (ID, position) values ("+ idUnderling+ ", '"+ position +"');";
            jdbcTemplate.execute(insertIntoEmployeeStatement);
            String insertIntoUnderlingsStatement = "insert into underlings (id_superior, id_underling) values ("+ idSuperior+ ", "+ idUnderling +");";
            jdbcTemplate.execute(insertIntoUnderlingsStatement);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);}
        }
        else
        {
            Response response = new Response(0, "This employee id already exists.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping("/change-superior/{idUnderling}/{idSuperior}")
    private ResponseEntity<String> changeSuperior(@PathVariable("idUnderling") int idUnderling, @PathVariable("idSuperior") int idSuperior) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(idUnderling) == false)
            {
                Response response = new Response(0, "The employee with id " + idUnderling + " does not exist.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
            }
            else
            if (verifyId(idSuperior) == false) {
                Response response = new Response(0, "The superior with id " + idSuperior + " does not exist.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
            }
            else
            {
                String deleteStatement = "delete from underlings where id_underling="+ idUnderling+";";
                jdbcTemplate.execute(deleteStatement);
                String insertStatement = "insert into underlings (id_superior, id_underling) values ("+ idSuperior+ ", "+ idUnderling +");";
                jdbcTemplate.execute(insertStatement);
                Response response = new Response(1, "Succes.");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);}
        }
    @RequestMapping("/remove-underling/{idUnderling}")
    private ResponseEntity<String> removeUnderling(@PathVariable("idUnderling") int idUnderling) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(idUnderling) == false)
        {
            Response response = new Response(0, "The employee with id " + idUnderling + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }
        else
        {
            String deleteFromUnderlingsStatement = "delete from underlings where id_underling="+ idUnderling+";";
            jdbcTemplate.execute(deleteFromUnderlingsStatement);
            String deleteFromUnderlingsSuperiorStatement = "delete from underlings where id_superior="+ idUnderling+";";
            jdbcTemplate.execute(deleteFromUnderlingsSuperiorStatement);
            String deleteFromEmployeeStatement = "delete from employee where ID="+ idUnderling+";";
            jdbcTemplate.execute(deleteFromEmployeeStatement);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);}
    }

    @RequestMapping("/viewUnderlings/{userID}")
    public ResponseEntity<String> viewUnderlings(@PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(userID) == false)
        {
            Response response = new Response(0, "The employee with id " + userID + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }
        else {
            String selectNumberUnderlings = "select count(id_superior) from underlings where id_superior = " + userID;
            int numberUnderlings = jdbcTemplate.queryForObject(selectNumberUnderlings, Integer.class);
            if (numberUnderlings == 0) {
                Response response =
                        new Response(0, "The employee with id " + userID + " has not underlings");
                return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.BAD_REQUEST);
            } else {
                String selectUnderlings = "select ID, position from employee " +
                        " join underlings on ID = id_underling where id_superior = " + userID;
                RowMapper<Underlings> rowMapper = new BeanPropertyRowMapper<Underlings>(Underlings.class);
                Response response = new Response(1, "success");
                Response1 response1 =
                        new Response1(response, this.jdbcTemplate.query(selectUnderlings, rowMapper));
                return new ResponseEntity<String>(response1.toString(), responseHeaders, HttpStatus.OK);
            }
        }
    @RequestMapping("/remove-underling/{idUnderling}")
    private ResponseEntity<String> removeUnderling(@PathVariable("idUnderling") int idUnderling) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        if (verifyId(idUnderling) == false)
        {
            Response response = new Response(0, "The employee with id " + idUnderling + " does not exist.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.NOT_FOUND);
        }
        else
        {
            String deleteFromUnderlingsStatement = "delete from underlings where id_underling="+ idUnderling+";";
            jdbcTemplate.execute(deleteFromUnderlingsStatement);
            String deleteFromUnderlingsSuperiorStatement = "delete from underlings where id_superior="+ idUnderling+";";
            jdbcTemplate.execute(deleteFromUnderlingsSuperiorStatement);
            String deleteFromEmployeeStatement = "delete from employee where ID="+ idUnderling+";";
            jdbcTemplate.execute(deleteFromEmployeeStatement);
            Response response = new Response(1, "Succes.");
            return new ResponseEntity<String>(response.toString(), responseHeaders, HttpStatus.OK);}
    }
}

    }

