package com.lekhya.batch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lekhya.batch.exception.ResourceNotFoundException;
import com.lekhya.batch.model.Person;
import com.lekhya.batch.repository.PersonRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BatchController {
	@Autowired
	private PersonRepository PersonRepository;

	@GetMapping("/Persons")
	public List<Person> getAllPersons() {
		return PersonRepository.findAll();
	}

	@GetMapping("/Persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long PersonId)
			throws ResourceNotFoundException {
		Person Person = PersonRepository.findById(PersonId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + PersonId));
		return ResponseEntity.ok().body(Person);
	}

	@PostMapping("/Persons")
	public Person createPerson(@Valid @RequestBody Person Person) {
		return PersonRepository.save(Person);
	}

	@PutMapping("/Persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long PersonId,
			@Valid @RequestBody Person PersonDetails) throws ResourceNotFoundException {
		Person Person = PersonRepository.findById(PersonId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + PersonId));

		Person.setEmail(PersonDetails.getEmail());
		Person.setLastName(PersonDetails.getLastName());
		Person.setFirstName(PersonDetails.getFirstName());
		Person.setAge(PersonDetails.getAge());
		final Person updatedPerson = PersonRepository.save(Person);
		return ResponseEntity.ok(updatedPerson);
	}

	@DeleteMapping("/Persons/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long PersonId)
			throws ResourceNotFoundException {
		Person Person = PersonRepository.findById(PersonId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + PersonId));

		PersonRepository.delete(Person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
