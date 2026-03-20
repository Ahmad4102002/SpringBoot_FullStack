package com.dailycodeworks.newapp.controllers;

import com.dailycodeworks.newapp.services.PersonService;
import com.dailycodeworks.newapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class First {

    @Autowired
    PersonService personService;

    @GetMapping("/people")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @GetMapping("/bye")
    public String bye() {
        return "Goodbye World";
    }

    @GetMapping("/person")
    public Person getPerson(){
        return new Person(1,"John",25);
    }
    @GetMapping("/greet/{name}")
    public String greetPerson(@PathVariable String name ){
        return "Hello" + name + "!";
    }
    @PostMapping("/people")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }
    @GetMapping("/people/{id}")
    public Person getPersonById(@PathVariable Integer id){
        return personService.getPersonById(id);
    }
    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
        return "Person with id" + id + "deleted!";
    }
    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person){
        Person updated = personService.updatePerson(id, person);

        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

}
