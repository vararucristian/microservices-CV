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

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/viewinfo/{userID}")

    public ResponseEntity<String> getAllUserNames(@PathVariable("userID") String userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.getInfoProfile(userID).toString(), responseHeaders, HttpStatus.OK);

    }

    @GetMapping(path = "/changeinfo/name/{dorinta}/{userID}")
    public ResponseEntity<String> changename(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changename(dorinta,userID), responseHeaders, HttpStatus.OK);

    }

    @GetMapping(path = "/changeinfo/prenume/{dorinta}/{userID}")
    public ResponseEntity<String> changeprenume(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeprenume(dorinta,userID), responseHeaders, HttpStatus.OK);}


    @GetMapping(path = "/changeinfo/email/{dorinta}/{userID}")
    public ResponseEntity<String> changeemail(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeemail(dorinta,userID), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeinfo/adress/{dorinta}/{userID}")
    public ResponseEntity<String> changeadress(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeadress(dorinta,userID), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeinfo/username/{dorinta}/{userID}")
    public ResponseEntity<String> changeusername(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeusername(dorinta,userID), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeposition/position_in_company/{dorinta}/{userID}")
    public ResponseEntity<String> changeposition(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeposition(dorinta,userID), responseHeaders, HttpStatus.OK);}

    @GetMapping(path = "/changeinfo/age/{dorinta}/{userID}")
    public ResponseEntity<String> changeage(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changeage(dorinta,userID), responseHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/changeinfo/gender/{dorinta}/{userID}")
    public ResponseEntity<String> changegender(@PathVariable("dorinta") String dorinta, @PathVariable("userID") int userID) throws URISyntaxException {
        URI location = new URI("localhost");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(userRepository.changegender(dorinta,userID), responseHeaders, HttpStatus.OK);}
}
