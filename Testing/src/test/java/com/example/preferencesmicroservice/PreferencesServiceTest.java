package com.example.preferencesmicroservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferencesServiceTest {
    @Autowired
    PreferencesService preferencesService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void verifyIdTest(){
        String selectMaxId = "select max(id_user) from preferences";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class) + 1;
        String insert = "insert into preferences values ( " + maxId + ", \'op1\', \'op2\', \'op3\')";
        jdbcTemplate.execute(insert);
        assertTrue(preferencesService.verifyId(maxId));
        String delete = "delete from preferences where id_user = " + maxId;
        jdbcTemplate.execute(delete);
        assertFalse(preferencesService.verifyId(maxId));
    }

    @Test
    public void notFoundEditPreferencesTest(){
        String selectMaxId = "select max(id_user) from preferences";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        assertEquals(new Response(0, "The profile with id "
                + (maxId + 1) + " does not exist.", HttpStatus.NOT_FOUND),
                preferencesService.editPreferences(maxId+1, "time", "pref"));
        assertEquals(new Response(0, "The time of the day time"
                + " does not exist.", HttpStatus.NOT_FOUND),
                preferencesService.editPreferences(maxId, "time", "pref"));
        assertEquals(new Response(0, "The preference pref"
                 + " does not exist.", HttpStatus.NOT_FOUND),
                preferencesService.editPreferences(maxId, "morning", "pref"));
    }

    @Test
    public void conflictEditPreferencesTest(){
        String selectMaxId = "select max(id_user) from preferences";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        String insert = "insert into preferences values(" + (maxId + 1) + " ,\'tired\', \'op2\', \'op3\')";
        jdbcTemplate.execute(insert);
        assertEquals(new Response(0, "The preference of user with the id "
                + (maxId + 1) + " is already set to tired for morning", HttpStatus.CONFLICT),
                preferencesService.editPreferences(maxId + 1, "morning", "tired"));
        String delete = "delete from preferences where id_user = " + (maxId + 1);
        jdbcTemplate.execute(delete);
    }

    @Test
    public void successEditPreferencesTest(){
        String selectMaxId = "select max(id_user) from preferences";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        String insert = "insert into preferences values(" + (maxId + 1)+ " ,\'op1\', \'op2\', \'op3\')";
        jdbcTemplate.execute(insert);
        assertEquals(new Response(1, "Succes.", HttpStatus.OK),
                preferencesService.editPreferences(maxId + 1, "morning", "tired"));
        String delete = "delete from preferences where id_user = " + (maxId + 1);
        jdbcTemplate.execute(delete);
    }

    @Test
    public void notFoundDeletePreferencesTest(){
        String selectMaxId = "select max(id_user) from preferences";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        assertEquals(new Response(0, "The profile with id "
                        + (maxId + 1) + " does not exist.", HttpStatus.NOT_FOUND),
                preferencesService.deletePreferences(maxId + 1));
    }

    @Test
    public void successDeletePreferencesTest() {
        String selectMaxId = "select max(id_user) from preferences";
        int maxId = jdbcTemplate.queryForObject(selectMaxId, Integer.class);
        String delete = "delete from preferences where id_user = " + (maxId + 1);
        jdbcTemplate.execute(delete);
        String insert = "insert into preferences values (" + (maxId+1) + " ,\'op1\', \'op2\', \'op3\')";
        jdbcTemplate.execute(insert);
        assertEquals(new Response(1, "Succes.", HttpStatus.OK),
                preferencesService.deletePreferences(maxId + 1));
    }

}
