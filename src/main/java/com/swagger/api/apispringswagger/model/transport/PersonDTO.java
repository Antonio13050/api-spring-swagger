package com.swagger.api.apispringswagger.model.transport;

import com.swagger.api.apispringswagger.model.Person;

public record PersonDTO(String guid,
                        String name,
                        String email) {

    public PersonDTO(Person person){
        this(person.getGuid(), person.getName(), person.getEmail());
    }
}
