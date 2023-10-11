package by.flameksandr.covidinformationsystem.controller;

import by.flameksandr.covidinformationsystem.domain.Person;
import by.flameksandr.covidinformationsystem.exception.PersonNotFoundException;
import by.flameksandr.covidinformationsystem.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class PersonController {
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage(){
        return new ModelAndView("addUser", "person", new Person());
    }
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) throws SQLException {
        if(bindingResult.hasErrors()){
            return "addUser";
        }
        personService.addPerson(person);

        return "redirect:/";
    }

    @RequestMapping("/patients")
    public ModelAndView getUsersPage(){
        return new ModelAndView("patients","patients",personService.getAllPatients());
    }

    @RequestMapping(value="/updateCovidInfo/{id}", method= RequestMethod.POST)
    public String updateStatus(@PathVariable("id") int id){
        personService.updatePersonCovidInfo(id);
        return "redirect:/patients";
    }

    @RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.POST)
    public String handleItemDelete(@PathVariable("id") int id) {
        personService.deletePersonById(id);
        return "redirect:/patients";
    }
}