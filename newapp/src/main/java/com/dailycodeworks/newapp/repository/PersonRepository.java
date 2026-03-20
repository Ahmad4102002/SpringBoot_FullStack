package com.dailycodeworks.newapp.repository;

import com.dailycodeworks.newapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
