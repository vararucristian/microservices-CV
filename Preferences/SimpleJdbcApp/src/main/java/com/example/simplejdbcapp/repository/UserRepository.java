package com.example.simplejdbcapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONObject;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class UserRepository {

 // we are autowiring jdbc template, 
 // using the properties we have configured spring automatically 
 // detects and creates jdbc template using the configuration
 @Autowired
 private JdbcTemplate jdbcTemplate;
 
 
 public JSONObject getAllPreferences(String accId) {
     List<String> preferencesList = new ArrayList<>();
     preferencesList.addAll(jdbcTemplate.queryForList("select 08_10 from preferences where acc_id="+accId, String.class));
     preferencesList.addAll(jdbcTemplate.queryForList("select 10_12 from preferences where acc_id="+accId, String.class));
     preferencesList.addAll(jdbcTemplate.queryForList("select 12_14 from preferences where acc_id="+accId, String.class));
     preferencesList.addAll(jdbcTemplate.queryForList("select 14_16 from preferences where acc_id="+accId, String.class));
     preferencesList.addAll(jdbcTemplate.queryForList("select 16_18 from preferences where acc_id="+accId, String.class));
     preferencesList.addAll(jdbcTemplate.queryForList("select 18_20 from preferences where acc_id="+accId, String.class));


     JSONObject preference = new JSONObject();
     preference.appendField("08_10",preferencesList.get(0));
     preference.appendField("10_12",preferencesList.get(1));
     preference.appendField("12_14",preferencesList.get(2));
     preference.appendField("14_16",preferencesList.get(3));
     preference.appendField("16_18",preferencesList.get(4));
     preference.appendField("18_20",preferencesList.get(5));

     return preference;
 }


 public void setDataSource(DataSource dataSource) {
     this.jdbcTemplate = new JdbcTemplate(dataSource);
 }

 public int changeH810(String pref, int accId, int taskId) {
     this.jdbcTemplate.update(
             "update preferences set 08_10=? where acc_id = ? and id_task = ?",
             pref,accId, taskId);
     return Response.SC_OK;
 }
 
 public int changeH1012(String pref, int accId, int taskId) {
     this.jdbcTemplate.update(
             "update preferences set 10_12=? where acc_id = ? and id_task = ?",
             pref,accId, taskId);
     return Response.SC_OK;
 }
 
 public int changeH1214(String pref, int accId, int taskId) {
     this.jdbcTemplate.update(
             "update preferences set 12_14=? where acc_id = ? and id_task = ?",
             pref,accId, taskId);
     return Response.SC_OK;
 }
 
 public int changeH1416(String pref, int accId, int taskId) {
     this.jdbcTemplate.update(
             "update preferences set 14_16=? where acc_id = ? and id_task = ?",
             pref,accId, taskId);
     return Response.SC_OK;
 }
 
 public int changeH1618(String pref, int accId, int taskId) {
     this.jdbcTemplate.update(
             "update preferences set 16_18=? where acc_id = ? and id_task = ?",
             pref,accId, taskId);
     return Response.SC_OK;
 }
 
 public int changeH1820(String pref, int accId, int taskId) {
     this.jdbcTemplate.update(
             "update preferences set 18_20=? where acc_id = ? and id_task = ?",
             pref,accId, taskId);
     return Response.SC_OK;
 }
 
 public int deletePref(int accId) {
	 this.jdbcTemplate.update(
			 "delete from preferences where acc_id = ?",
			 accId);
	 return Response.SC_OK;
 }


public int createPref(int acc_id, String h08_10, String h10_12, String h12_14, String h14_16, String h16_18,
		String h18_20) {
	
	Integer result = jdbcTemplate.queryForObject("select max(id_task) from preferences;", Integer.class);
	
	this.jdbcTemplate.update(
			"insert into preferences (id_task, acc_id, 08_10, 10_12, 12_14, 14_16, 16_18, 18_20) values"
			+ "(?, ?, ?, ?, ?, ?, ?, ?)",
			result + 1, acc_id, h08_10, h10_12, h12_14, h14_16, h16_18, h18_20);
	return Response.SC_CREATED;
	}
	
 
}