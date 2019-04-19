package com.javainspires.simplejdbcapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.javainspires.simplejdbcapp.UserProfile;
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
 
 
 public UserProfile getAllUserNames(String getusernames) {
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

  	return new UserProfile(userNameList.get(0),userNameList.get(1),userNameList.get(2),userNameList.get(3),userNameList.get(4),userNameList.get(5),Integer.parseInt(userNameList.get(7)),userNameList.get(8));
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