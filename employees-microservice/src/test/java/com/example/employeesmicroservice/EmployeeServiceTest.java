package com.example.employeesmicroservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void verifyIdTest(){
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) + 1;
        String insert = "insert into employee values(" + maxId + ", \'position\')";
        jdbcTemplate.execute(insert);
        assertTrue(employeeService.verifyId(maxId));
        String delete = "delete from employee where id = " + maxId;
        jdbcTemplate.execute(delete);
        assertFalse(employeeService.verifyId(maxId));
    }

    @Test
    public void verifyPositionTest(){
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) + 1;
        String insertTrue = "insert into employee values(" + maxId + ", \'human resources\')";
        String insertFalse = "insert into employee values(" + (maxId + 1) + ", \'position\')";
        jdbcTemplate.execute(insertTrue);
        jdbcTemplate.execute(insertFalse);
        assertEquals(employeeService.verifyPosition(maxId), new Response(1, "Succes", HttpStatus.OK));
        assertEquals(employeeService.verifyPosition(maxId + 1), new Response(0, "The employee with  id "
                + (maxId + 1) + " is not allowed to make changes.", HttpStatus.METHOD_NOT_ALLOWED));
        assertEquals(employeeService.verifyPosition(maxId + 2 ), new Response(0, "The id "
                + (maxId + 2) + " does not exist.", HttpStatus.NOT_FOUND));
        String delete = "delete from employee where id = " + maxId + " or id = " + (maxId + 1);
        jdbcTemplate.execute(delete);
    }
}
