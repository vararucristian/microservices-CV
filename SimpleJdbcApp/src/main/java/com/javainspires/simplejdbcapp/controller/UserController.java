package com.javainspires.simplejdbcapp.controller;

import java.util.List;

import com.javainspires.simplejdbcapp.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainspires.simplejdbcapp.repository.UserRepository;

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
 @GetMapping(path = "/viewinfo/{getusernames}")
 
 public UserProfile getAllUserNames(@PathVariable("getusernames") String getusernames) {
  return userRepository.getAllUserNames(getusernames);
 }
 
 @GetMapping(path = "/changeinfo/{camp}/{dorinta}/{getusernames}")
 public String changeProfile(@PathVariable("camp") String camp,@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
	  return (String) userRepository.changeProfile(camp, dorinta,getusernames);
	 }
 
}