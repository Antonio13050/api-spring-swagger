package com.swagger.api.apispringswagger.controller;

import com.swagger.api.apispringswagger.exceptions.PersonNotFoundException;
import com.swagger.api.apispringswagger.model.transport.PersonDTO;
import com.swagger.api.apispringswagger.model.transport.operations.CreatePersonForm;
import com.swagger.api.apispringswagger.model.transport.operations.UpdatePersonForm;
import com.swagger.api.apispringswagger.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findByGuid(@PathVariable("id") String guid) throws PersonNotFoundException {
        PersonDTO response = this.personService.findByGuid(guid);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody CreatePersonForm body, UriComponentsBuilder uriComponentsBuilder){
        PersonDTO response = this.personService.create(body);
        return ResponseEntity.created(uriComponentsBuilder.path("person/{id}").buildAndExpand(response.guid()).toUri()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) throws PersonNotFoundException {
        this.personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable("id") String id, @RequestBody @Valid UpdatePersonForm body) throws PersonNotFoundException {
        PersonDTO response = this.personService.update(id, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
