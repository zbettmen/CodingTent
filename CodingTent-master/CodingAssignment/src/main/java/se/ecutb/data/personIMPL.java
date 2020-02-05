package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component

public class personIMPL implements PersonRepository {
    private List<Person> personss = new ArrayList<>();

    @Override
    public Optional<Person> findById(int personId) {
        return personss.stream().filter(person -> person.getPersonId() == personId).findFirst();

    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {
        String emails =  person.getEmail();
        for (Person person1:personss){
            if (person1.getEmail().equalsIgnoreCase(emails)){
                throw new IllegalArgumentException("Persson alredy exists");
            }


        } personss.add(person);
        return person;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personss.stream().filter(person -> person.getEmail().equalsIgnoreCase(email)).findFirst();

    }

    @Override
    public List<Person> findByAddress(Address address) {
        if (address == null){
            return personss.stream().filter(person -> person.getAddress() == null)
                    .collect(Collectors.toList());

        }else {
            return personss.stream().filter(person -> person.getAddress() != null && person.getAddress().equals(address))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<Person> findByCity(String city) {
        List<Person> list = new ArrayList<>();
        for (Person person:personss){
            if (person.getAddress() != null && person.getAddress().getCity().equalsIgnoreCase(city)){
                    list.add(person);
            }
        }return list;
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personss.stream().filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        return personss.stream()
                .filter(person -> (person.getFirstName()+person.getLastName()).equalsIgnoreCase(fullName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return personss;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        Person p = findById(personId).get();
        if (personss.contains(p)){
            personss.remove(p);
                return true;
        }else return false;
    }

    @Override
    public void clear() {
        personss.clear();
    }
}
