package com.employees.employees.resources;

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
@RequestMapping("/employees")
public class EmployeesResource {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/fire/{id_hr}/{id_employee}")
    public ResponseEntity<String> fireEmployee(@PathVariable("id_hr") int id_hr, @PathVariable("id_employee") int id_employee) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        String result2 = "";

        String sql = "SELECT count(id) from employees where id = " + id_hr;
        int result =  jdbcTemplate.queryForObject(sql, Integer.class);
        String sql1 = "Select count(id) from employees where id = " + id_employee;
        if(result != 0){
            String sql2 = "Select position from employees where id = " + id_hr;
            result2 = jdbcTemplate.queryForObject(sql2, String.class);}

        int result1 = jdbcTemplate.queryForObject(sql1, Integer.class);

        if(result == 0)
            return new ResponseEntity<String>("The id "+ id_hr+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else if(result1 == 0)
            return new ResponseEntity<String>("The id "+ id_employee+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else if(result2.compareTo("Human Resources") != 0)
            return new ResponseEntity<String>("The employee with  id "+ id_hr+ " is not allowed to change someone's superior.", responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
        else{
            return new ResponseEntity<String>("The change was made.", responseHeaders, HttpStatus.OK);
        }
    }

    @RequestMapping("/changeSuperior/{id_hr}/{id_employee}/{id_newSuperior}")
    public ResponseEntity<String> changeSuperior(@PathVariable("id_hr") int id_hr, @PathVariable("id_employee") int id_employee, @PathVariable("id_newSuperior") int id_newSuperior) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        String result2 = "";

        String sql = "SELECT count(id) from employees where id = " + id_hr;
        int result =  jdbcTemplate.queryForObject(sql, Integer.class);

        if(result != 0){
            String sql2 = "Select position from employees where id = " + id_hr;
            result2 = jdbcTemplate.queryForObject(sql2, String.class);}

        String sql1 = "Select count(id) from employees where id = " + id_employee;
        int result1 = jdbcTemplate.queryForObject(sql1, Integer.class);

        String sql3 = "select count(id) from employees where id = " + id_newSuperior;
        int result3 = jdbcTemplate.queryForObject(sql3, Integer.class);

        if(result == 0)
            return new ResponseEntity<String>("The id "+ id_hr+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else if(result1 == 0)
            return new ResponseEntity<String>("The id "+ id_employee+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else if(result2.compareTo("Human Resources") != 0)
            return new ResponseEntity<String>("The employee with  id "+ id_hr+ " is not allowed to change someone's superior.", responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
        else if(result3 == 0)
            return new ResponseEntity<String>("The id "+ id_newSuperior+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else{
            return new ResponseEntity<String>("The change was made.", responseHeaders, HttpStatus.OK);
        }
    }

    @RequestMapping("/viewMySubordinates/{id}")
    public ResponseEntity<String> viewSubordinates (@PathVariable("id") int id) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        String verifySql= "select count(*) from employees where id="+id;
        int verifyId=jdbcTemplate.queryForObject(verifySql, Integer.class);
        if (verifyId==0)
            return new ResponseEntity<String>("The id "+ id+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else
        {
        String sql ="SELECT ID, firstName, lastName, position FROM employees e1 join supervision on ID=id_supervised where id_superviser="+id;
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
        //subordonatii subordonatilor
            return new ResponseEntity<String>(this.jdbcTemplate.query(sql, rowMapper).toString(), responseHeaders, HttpStatus.OK);
        // mesaj daca nu are subordonati
    }
    }


    @RequestMapping("/changeSalary/{id}/{id_subord}/{sum}")
    public ResponseEntity<String> changeSalary (@PathVariable("id") int id, @PathVariable("id_subord") int id_subord, @PathVariable("sum") int sum) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        String sql3= "select count(*) from employees where id="+id;
        int res2 = jdbcTemplate.queryForObject(sql3, Integer.class);
        if (res2 == 0)
            return new ResponseEntity<String>("The id "+ id+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else {
            String sql2 = "select count(*) from employees where id=" + id_subord;
            int res3 = jdbcTemplate.queryForObject(sql2, Integer.class);
            String sql1 = "select position from employees where id=" + id;
            String position = jdbcTemplate.queryForObject(sql1, String.class);
            if (res3 == 0)
                return new ResponseEntity<String>("The id "+ id_subord+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
            else {
                if (!position.equals("Human Resources")) {
                    return new ResponseEntity<String>("The employee with  id "+ id+ " is not allowed to change someone's salary.", responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
                } else {
                    String sql = "select Salary from employees where id=" + id_subord;
                    int res = jdbcTemplate.queryForObject(
                            sql, Integer.class);
                    //update
                    return new ResponseEntity<String>("The change was made.", responseHeaders, HttpStatus.OK);
                }
            }
        }
    }


    @RequestMapping("/changePosition/{id}/{id_subord}/{position}")
    public ResponseEntity<String> chengePosition (@PathVariable("id") int id, @PathVariable("id_subord") int id_subord, @PathVariable("position") String position) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        String sql3= "select count(*) from employees where id="+id;
        int res2 = jdbcTemplate.queryForObject(sql3, Integer.class);
        if (res2 == 0)
            return new ResponseEntity<String>("The id "+ id+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else {
        String sql2= "select count(*) from employees where id="+id_subord;
        int res3=jdbcTemplate.queryForObject(sql2, Integer.class);
        String sql1= "select position from employees where id="+id;
        String position1=jdbcTemplate.queryForObject(sql1, String.class);
        if (res3==0)
            return new ResponseEntity<String>("The id "+ id_subord+ " does not exist.", responseHeaders, HttpStatus.NOT_FOUND);
        else
        {
            if (!position1.equals("Human Resources")) {
                return new ResponseEntity<String>("The employee with  id "+ id+ " is not allowed to change someone's position.", responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);

            } else{
                String sql= "select Position from employees where id="+id_subord;
                String res =jdbcTemplate.queryForObject(sql, String.class);
                //update
                return new ResponseEntity<String>("The change was made.", responseHeaders, HttpStatus.OK);
            }}
    }
}}
