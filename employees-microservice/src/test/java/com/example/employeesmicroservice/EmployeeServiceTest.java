package com.example.employeesmicroservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void addUnderlingTest (){
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) ;
        assertEquals( new Response(0, "The superior with id " + (maxId+1 ) + " does not exist.", HttpStatus.NOT_FOUND),
                employeeService.addUnderling(maxId + 2  , maxId+1, "position"));
        assertEquals( new Response(0, "This employee id already exists.", HttpStatus.BAD_REQUEST),
                employeeService.addUnderling(maxId  , maxId, "position"));
        assertEquals( new Response(1, "Succes", HttpStatus.OK),
                employeeService.addUnderling(maxId + 1 , maxId, "position"));
        String deleteEmployee = "delete from employee where id = " + (maxId+1);
        String deleteUnderling= "delete from underlings where id_underling=" + (maxId+1);
        jdbcTemplate.execute(deleteUnderling);
        jdbcTemplate.execute(deleteEmployee);
    }

    @Test
    public void changeSuperiorTest(){
        String selectMaxId = "select max(id_superior) from underlings";
        String selectMinId ="select min(id_underling) from underlings";
        String selectMaxIdEmployee = "select max(id) from employee";
        String selectMinIdEmployee ="select min(id) from employee";
        // se mai poate verifica daca max chiar e seful lui min
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) ;
        int minId = jdbcTemplate.queryForObject(selectMinId, Integer.class);
        int maxIdEmployee = jdbcTemplate.queryForObject(selectMaxIdEmployee, Integer.class) ;
        int minIdEmployee = jdbcTemplate.queryForObject(selectMinIdEmployee, Integer.class);
        String selectRealSuperior ="select id_superior from underlings where id_underling="+ minId;
        int RealSuperiorId = jdbcTemplate.queryForObject(selectMinId, Integer.class);
        assertEquals(new Response(0, "The employee with id " + (minIdEmployee-1) + " does not exist.", HttpStatus.NOT_FOUND),
                employeeService.changeSuperior(minIdEmployee-1, maxId));
        assertEquals(new Response(0, "The superior with id " + (maxIdEmployee+1) + " does not exist.", HttpStatus.NOT_FOUND),
                employeeService.changeSuperior(maxId, maxIdEmployee+1));
        assertEquals(new Response(1, "Succes", HttpStatus.OK), employeeService.changeSuperior(minId, maxId));
        String delete = "delete from underlings where id_superior =" + maxId+ " and id_underling="+ minId;
        jdbcTemplate.execute(delete);
        String insert = "insert into underlings (id_superior, id_underling) values ( " +RealSuperiorId +" , "+ minId+" )";
        jdbcTemplate.execute(insert);
    }

    @Test
    public void removeUnderlingTest(){
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) ;
        assertEquals(new Response(0, "The employee with id " + (maxId+1) + " does not exist.", HttpStatus.NOT_FOUND),
                employeeService.removeUnderling(maxId+1));
        String insertEmployee = "insert into employee values(" + (maxId+1) + ", \'position\')";
        String insertUnderling = "insert into underlings values(" + (maxId) + ","+ (maxId+1)+")";
        jdbcTemplate.execute(insertEmployee);
        jdbcTemplate.execute(insertUnderling);
        assertEquals(new Response(1, "Succes.", HttpStatus.OK),
                employeeService.removeUnderling(maxId+1));

    }

    @Test
    public void viewUnderlingsTest() {
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) ;
        String insertSuperior = "insert into employee values(" + (maxId + 1) + ", \'position\')";
        jdbcTemplate.execute(insertSuperior);
        assertEquals(new Response1(new Response(0, "The employee with id "
                        + (maxId + 2) + " does not exist.", HttpStatus.NOT_FOUND), null),
                employeeService.viewUnderlings(maxId + 2));
        assertEquals(new Response1(new Response(0, "The employee with id "
                        + (maxId + 1) + " has not underlings", HttpStatus.BAD_REQUEST), null),
                employeeService.viewUnderlings(maxId + 1));
        String deleteSuperior = "delete from employee where id = " + (maxId + 1);
        jdbcTemplate.execute(deleteSuperior);
    }

    @Test
    public void getProfileUnderlingErrorTest() {
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        String insertEmployee = "insert into employee values(" + (maxId + 1) + ", \'position\')";
        String insertUnderling = "insert into underlings values(" + (maxId) + ", " + (maxId + 1) + ")";
        jdbcTemplate.execute(insertEmployee);
        jdbcTemplate.execute(insertUnderling);
        String selectUnderlings = "select ID, position from employee " +
                " join underlings on ID = id_underling where id_superior = " + maxId;
        RowMapper<Underling> rowMapper = new BeanPropertyRowMapper<Underling>(Underling.class);
        List<Underling> underlings =  this.jdbcTemplate.query(selectUnderlings, rowMapper);
        assertEquals(new Response1(new Response(0, "The underling with id "
                        + (maxId + 1) + " do not have a profile. ", HttpStatus.NOT_FOUND), null),
                employeeService.getProfileUnderlings(underlings));
        String deleteUnderling = "delete from underlings where id_underling = " + (maxId + 1);
        String deleteEmployee = "delete from employee where id = " + (maxId + 1);
        jdbcTemplate.execute(deleteUnderling);
        jdbcTemplate.execute(deleteEmployee);
    }

    @Test
    public void getProfileUnderlingsCorrectTest() {
        String selectMaxId = "select max(id) from employee";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        String insertEmployee = "insert into employee values(" + (maxId + 1) + ", \'position\')";
        String insertUnderling = "insert into underlings values(" + (maxId + 1) + ", " + maxId + ")";
        jdbcTemplate.execute(insertEmployee);
        jdbcTemplate.execute(insertUnderling);
        String selectUnderlings = "select ID, position from employee " +
                " join underlings on ID = id_underling where id_superior = " + (maxId + 1);
        RowMapper<Underling> rowMapper = new BeanPropertyRowMapper<Underling>(Underling.class);
        List<Underling> underlings =  this.jdbcTemplate.query(selectUnderlings, rowMapper);
        ResponseEntity<String> raspuns = null;
        List <UnderlingData> myUnderlings= new ArrayList<UnderlingData>();
        String url = "http://104.199.20.255:8100/profile/get-profile/" + maxId;
        raspuns=new RestTemplate().getForEntity(url, String.class);
        try {
            JSONArray obj = new JSONArray(raspuns.toString().substring(5));
            JSONArray obj2 = obj.getJSONArray(1);
            JSONObject obj1  = obj.optJSONObject(0);
            JSONObject profile = obj2.optJSONObject(0);
            if (profile != null){
                String email = profile.getString("email");
                myUnderlings.add(new UnderlingData(underlings.get(0), email));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(new Response1(new Response(1, "success", HttpStatus.OK), myUnderlings),
                employeeService.getProfileUnderlings(underlings));
        String deleteUnderling = "delete from underlings where id_superior = " + (maxId + 1);
        String deleteEmployee = "delete from employee where id = " + (maxId + 1);
        jdbcTemplate.execute(deleteUnderling);
        jdbcTemplate.execute(deleteEmployee);
    }
}
