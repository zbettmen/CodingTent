package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.TodoRepository;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonServiceIMPL implements PersonService{
    private TodoRepository todoRepository;
    private PersonDtoConversionService personDtoConversionService;
    private CreatePersonService createPersonService;
    private PersonRepository personRepository;
    private IdSequencers idSequencers;

    @Autowired
    public PersonServiceIMPL(CreatePersonService createPersonService, PersonRepository personRepository,
                             IdSequencers idSequencers,PersonDtoConversionService personDtoConversionService, TodoRepository todoRepository) {
        this.createPersonService = createPersonService;
        this.personRepository = personRepository;
        this.idSequencers = idSequencers;
        this.personDtoConversionService = personDtoConversionService;
        this.todoRepository = todoRepository;
    }

    @Override
    public Person createPerson(String firstName, String lastName, String email, Address address) {
        return personRepository.persist(createPersonService.create(
                firstName,
                lastName,
                email,
                address));
    }

    @Override
    public List<PersonDto> findAll() {
        List<PersonDto> personDtoList = personRepository.findAll().stream()
                .map(person -> personDtoConversionService
                  .convertToPersonDto(person))
                .collect(Collectors.toList());
        return personDtoList;
    }

    @Override
    public PersonDto findById(int personId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Person findByEmail(String email) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<PersonDtoWithTodo> findPeopleWithAssignedTodos() {
        return null;
    }

    @Override
    public List<PersonDto> findAllPeopleWithNoTodos() {
        return null;
    }

    @Override
    public List<PersonDto> findPeopleByAddress(Address address) {
        return null;
    }

    @Override
    public List<PersonDto> findPeopleByCity(String city) {
        return null;
    }

    @Override
    public List<PersonDto> findByFullName(String fullName) {
        return personRepository.findAll().stream()
                .filter(person -> (person.getFirstName()+" "+person.getLastName()).equalsIgnoreCase(fullName))
                .map(person -> personDtoConversionService.convertToPersonDto(person))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> findByLastName(String lastName) {
        return personRepository.findAll().stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .map(person -> personDtoConversionService
                        .convertToPersonDto(person))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deletePerson(int personId) throws IllegalArgumentException {
        return false;
    }
}
