package com.example.simplejdbcapp.controller;

//import java.util.List;

import net.minidev.json.JSONObject;
import com.example.simplejdbcapp.Preferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplejdbcapp.repository.UserRepository;

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
 public String changeH810(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH810(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/10_12/{pref}/{accId}/{taskId}")
 public String changeH1012(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH1012(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/12_14/{pref}/{accId}/{taskId}")
 public String changeH1214(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH1214(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/14_16/{pref}/{accId}/{taskId}")
 public String changeH1416(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH1416(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/16_18/{pref}/{accId}/{taskId}")
 public String changeH1618(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH1618(pref, accId, taskId);
 }
 
 @GetMapping(path = "/changePreference/18_20/{pref}/{accId}/{taskId}")
 public String changeH1820(@PathVariable("pref") String pref,@PathVariable("accId") int accId, @PathVariable("taskId") int taskId) {
     return (String) userRepository.changeH1820(pref, accId, taskId);
 }
}