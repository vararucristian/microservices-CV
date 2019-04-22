package com.example.Profil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;


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
    @RequestMapping("/viewinfo/{getusernames}")

    public ResponseEntity<String> getAllUserNames(@PathVariable("getusernames") String getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.getAllUserNames(getusernames).toString(), responseHeaders, HttpStatus.OK);

    }

    @GetMapping(path = "/changeinfo/name/{dorinta}/{getusernames}")
    public ResponseEntity<String> changename(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changename(dorinta,getusernames), responseHeaders, HttpStatus.OK);

    }

    @GetMapping(path = "/changeinfo/prenume/{dorinta}/{getusernames}")
    public ResponseEntity<String> changeprenume(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeprenume(dorinta,getusernames), responseHeaders, HttpStatus.OK);}


    @GetMapping(path = "/changeinfo/email/{dorinta}/{getusernames}")
    public ResponseEntity<String> changeemail(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeemail(dorinta,getusernames), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeinfo/adress/{dorinta}/{getusernames}")
    public ResponseEntity<String> changeadress(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeadress(dorinta,getusernames), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeinfo/username/{dorinta}/{getusernames}")
    public ResponseEntity<String> changeusername(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeusername(dorinta,getusernames), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeposition/position_in_company/{dorinta}/{getusernames}")
    public ResponseEntity<String> changeposition(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeposition(dorinta,getusernames), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeinfo/age/{dorinta}/{getusernames}")
    public ResponseEntity<String> changeage(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeage(dorinta,getusernames), responseHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/changeinfo/gender/{dorinta}/{getusernames}")
    public ResponseEntity<String> changegender(@PathVariable("dorinta") String dorinta, @PathVariable("getusernames") int getusernames) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changegender(dorinta,getusernames), responseHeaders, HttpStatus.OK);}
}
