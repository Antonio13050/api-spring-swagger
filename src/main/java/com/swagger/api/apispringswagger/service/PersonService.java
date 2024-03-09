package com.swagger.api.apispringswagger.service;

import com.swagger.api.apispringswagger.exceptions.PersonNotFoundException;
import com.swagger.api.apispringswagger.model.Person;
import com.swagger.api.apispringswagger.model.transport.PersonDTO;
import com.swagger.api.apispringswagger.model.transport.operations.CreatePersonForm;
import com.swagger.api.apispringswagger.model.transport.operations.UpdatePersonForm;
import com.swagger.api.apispringswagger.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public PersonDTO create(CreatePersonForm form){
        String passwordEncoded = this.passwordEncoder.encode(form.password());
        Person person = new Person(form, passwordEncoded);
        this.personRepository.save(person);
        return new PersonDTO(person);
    }

    public PersonDTO findByGuid(String guid) throws PersonNotFoundException {
        return this.personRepository.findByGuid(guid)
                .map(PersonDTO::new)
                .orElseThrow(() -> new PersonNotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public void delete(String guid) throws PersonNotFoundException {
        Person person = this.personRepository.findByGuid(guid)
                .orElseThrow(() -> new PersonNotFoundException("Usuário não encontrado"));
        this.personRepository.deleteById(guid);
    }

    @Transactional
    public PersonDTO update(String guid, UpdatePersonForm form) throws PersonNotFoundException {
        Person person = this.personRepository.findByGuid(guid)
                .orElseThrow(() -> new PersonNotFoundException("Usuário não encontrado"));
        person.setName(form.name());
        person.setEmail(form.email());

        return new PersonDTO(person);
    }
}
