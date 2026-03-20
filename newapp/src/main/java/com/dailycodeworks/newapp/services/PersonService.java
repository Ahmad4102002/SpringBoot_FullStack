package com.dailycodeworks.newapp.services;

import com.dailycodeworks.newapp.repository.PersonRepository;
import com.dailycodeworks.newapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository; // now we can directly use the crud methods

    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }
    public Person addPerson(Person person){
        return personRepository.save(person);
    }
    public Person getPersonById(Integer id){
        return personRepository.findById(id).orElse(null);
    }
    public void deletePerson(Integer id){
        personRepository.deleteById(id);
    }
    public Person updatePerson(Integer id, Person person){
        Person existing = personRepository.findById(id).orElse(null);
        if (existing == null){
            return null;
        }
        existing.setName(person.getName());
        existing.setAge(person.getAge());

        return personRepository.save(existing);

    }
}
