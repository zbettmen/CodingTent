package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.PersonRepository;
import se.ecutb.model.*;

import java.time.LocalDate;
import java.util.List;

@Component
public class CreatePersonServiceIMPL extends AbstractPersonFactory implements CreatePersonService {


    private PersonRepository personRepository;
    private IdSequencers idSequencers;

    @Autowired
    public CreatePersonServiceIMPL(PersonRepository personRepository, IdSequencers idSequencers) {
        this.personRepository = personRepository;
        this.idSequencers = idSequencers;
    }
    @Override
    public Person create(String firstName, String lastName, String email) throws IllegalArgumentException {
        List<Person> personList = personRepository.findAll();
        for (Person person:personList) {
            if (person.getEmail().equalsIgnoreCase(email)){
                throw new IllegalArgumentException("user with that email already exists");
            }
        }
        return createNewPerson(idSequencers.nextPersonId(),firstName,lastName,email,null);
    }

    @Override
    public Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException {
        List<Person> personList = personRepository.findAll();
        for (Person person:personList) {
            if (person.getEmail().equalsIgnoreCase(email)){
                throw new IllegalArgumentException("user with that email already exists");
            }
        }
        return createNewPerson(idSequencers.nextPersonId(),firstName,lastName,email,address);
    }
}
