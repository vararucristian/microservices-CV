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
public class EmployeesController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/try-get/{idEmployee}")
    private ResponseEntity<String> verifyPosition(@PathVariable("idEmployee") int idEmployee) {
        URI location = null;
        try {
            location = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
       // EmployeeService employeeService = new EmployeeService();
        Response response = employeeService.verifyPosition(idEmployee);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }

    @RequestMapping("/add-underling/{idUnderling}/{idSuperior}/{position}")
    private ResponseEntity<String> addUnderling(@PathVariable("idUnderling") int idUnderling, @PathVariable("idSuperior") int idSuperior, @PathVariable("position") String position) {
        URI location = null;
        try {
            location = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        //EmployeeService employeeService = new EmployeeService();
        Response response = employeeService.addUnderling(idUnderling, idSuperior, position);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }

    @RequestMapping("/change-superior/{idUnderling}/{idSuperior}")
    private ResponseEntity<String> changeSuperior(@PathVariable("idUnderling") int idUnderling, @PathVariable("idSuperior") int idSuperior) {
        URI location = null;
        try {
            location = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        //EmployeeService employeeService = new EmployeeService();
        Response response = employeeService.changeSuperior(idUnderling, idSuperior);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }

    @RequestMapping("/remove-underling/{idUnderling}")
    private ResponseEntity<String> removeUnderling(@PathVariable("idUnderling") int idUnderling) {
        URI location = null;
        try {
            location = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        //EmployeeService employeeService = new EmployeeService();
        Response response = employeeService.removeUnderling(idUnderling);
        return new ResponseEntity<String>(response.toString(), responseHeaders, response.getStatus());
    }

    @RequestMapping("/viewUnderlings/{userID}")
    public ResponseEntity<String> viewUnderlings(@PathVariable("userID") int userID) {
        URI location = null;
        try {
            location = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        //EmployeeService employeeService = new EmployeeService();
        Response1 response1 = employeeService.viewUnderlings(userID);
        return new ResponseEntity<String>(response1.toString(), responseHeaders, response1.getResponse().getStatus());
    }
    @RequestMapping("/get-position/{userID}")
    public ResponseEntity<String> getPosition(@PathVariable("userID") int userID) {
        URI location = null;
        try {
            location = new URI("localhost");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        //EmployeeService employeeService = new EmployeeService();
        ResponsePosition responsePosition = employeeService.getPosition(userID);
        return new ResponseEntity<String>(responsePosition.toString(), responseHeaders, responsePosition.getResponse().getStatus());
    }
}

