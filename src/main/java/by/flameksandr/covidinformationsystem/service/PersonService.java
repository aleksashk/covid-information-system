package by.flameksandr.covidinformationsystem.service;

import by.flameksandr.covidinformationsystem.domain.Person;
import by.flameksandr.covidinformationsystem.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {
    void addPerson(Person person);
    List<Person> getAllPatients();
    Person getPersonById(int id);
    void updatePersonCovidInfo(int id);
    void deletePersonById(int id);


}