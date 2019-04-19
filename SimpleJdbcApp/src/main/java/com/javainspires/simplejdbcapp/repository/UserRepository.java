package com.javainspires.simplejdbcapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;



@Repository
public class UserRepository {

 // we are autowiring jdbc template, 
 // using the properties we have configured spring automatically 
 // detects and creates jdbc template using the configuration
 @Autowired
 private JdbcTemplate jdbcTemplate;
 
 
 public String getAllUserNames(String getusernames) {
  List<String> userNameList = new ArrayList<>();
  userNameList.addAll(jdbcTemplate.queryForList("select name from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select last_name from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select email from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select adress from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select username from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select position_in_company from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select account_id from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select age from profile where account_id="+getusernames, String.class));
  userNameList.addAll(jdbcTemplate.queryForList("select gender from profile where account_id="+getusernames, String.class));
  
  String c="Nume : "+userNameList.get(1) + '\n' + "Prenume : "+userNameList.get(0)+'\n'+"Email : "+userNameList.get(2)+'\n'+"Adress : "+userNameList.get(3)+'\n'
		   +"Username : "+userNameList.get(4)+'\n'+"Position in company : "+userNameList.get(5)+'\n'+"Account ID : "+userNameList.get(6)+'\n'+"Age : "+userNameList.get(7)+'\n'+
		   "Gender : "+userNameList.get(8)+'\n' ; 
  c = c.replaceAll("(\r\n|\n)", "<br />");
  	return c;
 }


 public void setDataSource(DataSource dataSource) {
     this.jdbcTemplate = new JdbcTemplate(dataSource);
 }

 public String changeProfile(String camp,String dorinta,int getusernames) {
     this.jdbcTemplate.update(
    		 "update profile set ? = ? where account_id = ?",  
             camp,dorinta,getusernames);
	return "OK";
 }

}