package com.example.Profil;
import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;



@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public JSONObject getInfoProfile(String userID) {
        List<String> userNameList = new ArrayList<>();
        userNameList.addAll(jdbcTemplate.queryForList("select name from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select last_name from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select email from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select adress from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select username from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select position_in_company from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select account_id from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select age from profile where account_id="+userID, String.class));
        userNameList.addAll(jdbcTemplate.queryForList("select gender from profile where account_id="+userID, String.class));


        JSONObject profile= new JSONObject();
        profile.appendField("fname",userNameList.get(0));
        profile.appendField("lname",userNameList.get(1));
        profile.appendField("email",userNameList.get(2));
        profile.appendField("adress",userNameList.get(3));
        profile.appendField("username",userNameList.get(4));
        profile.appendField("position",userNameList.get(5));
        profile.appendField("age",userNameList.get(7));
        profile.appendField("gender",userNameList.get(8));

        return profile;
    }


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String changename(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set name=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changeprenume(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set last_name=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changeemail(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set email=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changeadress(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set adress=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changeusername(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set username=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changeposition(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set position_in_company=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changeage(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set age=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

    public String changegender(String dorinta,int userID) {
        this.jdbcTemplate.update(
                "update profile set gender=? where account_id = ?",
                dorinta,userID);
        return "Succes";
    }

}
