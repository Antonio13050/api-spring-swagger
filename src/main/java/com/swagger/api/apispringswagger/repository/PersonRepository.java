package com.swagger.api.apispringswagger.repository;

import com.swagger.api.apispringswagger.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByGuid(String guid);
}
