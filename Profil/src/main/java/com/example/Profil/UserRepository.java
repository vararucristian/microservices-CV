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

    // we are autowiring jdbc template,
    // using the properties we have configured spring automatically
    // detects and creates jdbc template using the configuration
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public JSONObject getAllUserNames(String getusernames) {
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


        JSONObject profile= new JSONObject();
        profile.appendField("fname",userNameList.get(0));
        profile.appendField("lname",userNameList.get(1));
        profile.appendField("email",userNameList.get(2));
        profile.appendField("adress",userNameList.get(3));
        profile.appendField("username",userNameList.get(4));
        profile.appendField("position",userNameList.get(5));
        profile.appendField("age",userNameList.get(6));
        profile.appendField("gender",userNameList.get(7));

        return profile;
    }


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String changename(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set name=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changeprenume(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set last_name=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changeemail(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set email=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changeadress(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set adress=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changeusername(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set username=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changeposition(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set position_in_company=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changeage(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set age=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

    public String changegender(String dorinta,int getusernames) {
        this.jdbcTemplate.update(
                "update profile set gender=? where account_id = ?",
                dorinta,getusernames);
        return "200";
    }

}