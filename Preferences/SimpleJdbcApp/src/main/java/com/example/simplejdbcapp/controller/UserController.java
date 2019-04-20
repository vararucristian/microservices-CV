package com.example.simplejdbcapp.controller;

//import java.util.List;

import com.example.simplejdbcapp.repository.UserRepository;
import net.minidev.json.JSONObject;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/user")
public class UserController {

 // autowiring user repository
 @Autowired
 UserRepository userRepository;

 @GetMapping
 public String toTest() {
  return "Welcome to Java Inspires...";
 }

 /**
  * this method returns list of usernames
  * 
  * @return username list
  */
 @RequestMapping("/getPreferences/{accId}")
 
 public JSONObject getAllPreferences(@PathVariable("accId") String accId) {
     return userRepository.getAllPreferences(accId);
 }
 
 @GetMapping(path = "/changePreference/08_10/{pref}/{accId}/{taskId}")
 public int changeH810(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return userRepository.changeH810(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/10_12/{pref}/{accId}/{taskId}")
 public String changeH1012(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH1012(pref, accId, taskId);
  Response.SC_CREATED.\
 }
 
 @GetMapping(path = "/changePreference/12_14/{pref}/{accId}/{taskId}")
 public int changeH1214(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return userRepository.changeH1214(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/14_16/{pref}/{accId}/{taskId}")
 public int changeH1416(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return userRepository.changeH1416(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/16_18/{pref}/{accId}/{taskId}")
 public int changeH1618(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return userRepository.changeH1618(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/18_20/{pref}/{accId}/{taskId}")
 public int changeH1820(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return userRepository.changeH1820(pref, accId, taskId);
 }
 
 @GetMapping(path = "/deletePreference/{accId}")
 public int deletePref(@PathVariable("accId") int accId) {
	 return userRepository.deletePref(accId);
 }
 
 @GetMapping(path = "/createPreference/{acc_id}/{h08_10}/{h10_12}/{h12_14}/{h14_16}/{h16_18}/{h18_20}")
 public int createPref(@PathVariable("acc_id") int acc_id, @PathVariable("h08_10") String h08_10, @PathVariable("h10_12") String h10_12, @PathVariable("h12_14") String h12_14, @PathVariable("h14_16") String h14_16, @PathVariable("h16_18") String h16_18, @PathVariable("h18_20") String h18_20) {
	return userRepository.createPref(acc_id, h08_10, h10_12, h12_14, h14_16, h16_18, h18_20); 
}
}