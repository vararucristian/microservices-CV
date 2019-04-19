package com.employees.employees.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeesResource {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/fire/{id_hr}/{id_employee}")
    public List<Object> fireEmployee(@PathVariable("id_hr") int id_hr, @PathVariable("id_employee") int id_employee){

        String result2 = "";

        String sql = "SELECT count(id) from employees where id = " + id_hr;
        int result =  jdbcTemplate.queryForObject(sql, Integer.class);
        String sql1 = "Select count(id) from employees where id = " + id_employee;
        if(result != 0){
            String sql2 = "Select position from employees where id = " + id_hr;
            result2 = jdbcTemplate.queryForObject(sql2, String.class);}

        int result1 = jdbcTemplate.queryForObject(sql1, Integer.class);

        if(result == 0)
            return Collections.singletonList("The employee with id " + id_hr + " does not exist");
        else if(result1 == 0)
                return Collections.singletonList("The employee with id " + id_employee + " does not exist");
        else if(result2.compareTo("Human Resources") != 0)
                return Collections.singletonList("The employee with id " + id_hr + " can't fire another employee");
        else{
            return Collections.singletonList("The employee was fired");
        }
    }

    @RequestMapping("/changeSuperior/{id_hr}/{id_employee}/{id_newSuperior}")
    public List<Object> changeSuperior(@PathVariable("id_hr") int id_hr, @PathVariable("id_employee") int id_employee, @PathVariable("id_newSuperior") int id_newSuperior){

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
            return Collections.singletonList("The employee with id " + id_hr + " does not exist");
        else if(result1 == 0)
            return Collections.singletonList("The employee with id " + id_employee + " does not exist");
        else if(result2.compareTo("Human Resources") != 0)
            return Collections.singletonList("The employee with id " + id_hr + " can't change the superior to other employees");
        else if(result3 == 0)
            return Collections.singletonList("The employee with id " + id_newSuperior + " does not exist");
        else{
            return Collections.singletonList("The superior was changed");
        }
    }

    @RequestMapping("/viewMySubordinates/{id}")
    public List<Employee> viewSubordinates (@PathVariable("id") int id) {

        String sql ="SELECT ID, firstName, lastName, position FROM employees e1 join supervision on ID=id_supervised where id_superviser="+id;
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
        //subordonatii subordonatilor
        return this.jdbcTemplate.query(sql, rowMapper);
        // mesaj daca nu are subordonati/ nu e corect id-ul
    }
    @RequestMapping("/changeSalary/{id}/{id_subord}/{sum}")
    public List<Object> changeSalary (@PathVariable("id") int id, @PathVariable("id_subord") int id_subord, @PathVariable("sum") int sum)
    {

        String sql2= "select count(*) from employees where id="+id_subord;
        int res2=jdbcTemplate.queryForObject(sql2, Integer.class);
        String sql1= "select position from employees where id="+id;
        String position=jdbcTemplate.queryForObject(sql1, String.class);
        if (res2==0)
            return Collections.singletonList("The employee with ID " + id_subord + " does not exist!");
        else
        {
            if (!position.equals("Human Resources")) {
                return Collections.singletonList("You are not allowed to chenge someone's salary!");
            } else{
                String sql= "select Salary from employees where id="+id_subord;
                int res=jdbcTemplate.queryForObject(
                        sql, Integer.class);
                //update
                return Collections.singletonList("For employee with id " + id_subord + " the salary was " + res + " and now is " + sum + ".");
            }}}

    @RequestMapping("/changePosition/{id}/{id_subord}/{position}")
    public List<Object> chengePosition (@PathVariable("id") int id, @PathVariable("id_subord") int id_subord, @PathVariable("position") String position)
    {

        String sql2= "select count(*) from employees where id="+id_subord;
        int res2=jdbcTemplate.queryForObject(sql2, Integer.class);
        String sql1= "select position from employees where id="+id;
        String position1=jdbcTemplate.queryForObject(sql1, String.class);
        if (res2==0)
            return Collections.singletonList("The employee with ID " + id_subord + " does not exist!");
        else
        {
            if (!position1.equals("Human Resources")) {
                return Collections.singletonList("You are not allowed to chenge someone's position!");
            } else{
                String sql= "select Position from employees where id="+id_subord;
                String res =jdbcTemplate.queryForObject(sql, String.class);
                //update
                return Collections.singletonList("For employee with id " + id_subord + " the position was " + res + " and now is " + position + ".");
            }}
    }
}
