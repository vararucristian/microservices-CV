package com.example.Profil;


import net.minidev.json.JSONObject;
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
    @RequestMapping("/viewinfo/{getusernames}")

    public JSONObject getAllUserNames(@PathVariable("getusernames") String getusernames) {
        return userRepository.getAllUserNames(getusernames);

    }

    @GetMapping(path = "/changeinfo/name/{dorinta}/{getusernames}")
    public String changenamee(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changename(dorinta,getusernames);
    }

    @GetMapping(path = "/changeinfo/prenume/{dorinta}/{getusernames}")
    public String changeprenume(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changeprenume(dorinta,getusernames);}


    @GetMapping(path = "/changeinfo/email/{dorinta}/{getusernames}")
    public String changeemail(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changeemail(dorinta,getusernames);}

    @GetMapping(path = "/changeinfo/adress/{dorinta}/{getusernames}")
    public String changeadress(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changeadress(dorinta,getusernames);}

    @GetMapping(path = "/changeinfo/username/{dorinta}/{getusernames}")
    public String changeusername(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changeusername(dorinta,getusernames);}

    @GetMapping(path = "/changeposition/position_in_company/{dorinta}/{getusernames}")
    public String changeposition(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changeposition(dorinta,getusernames);}

    @GetMapping(path = "/changeinfo/age/{dorinta}/{getusernames}")
    public String changeage(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changeage(dorinta,getusernames);}

    @GetMapping(path = "/changeinfo/gender/{dorinta}/{getusernames}")
    public String changegender(@PathVariable("dorinta") String dorinta,@PathVariable("getusernames") int getusernames) {
        return (String) userRepository.changegender(dorinta,getusernames);}
}