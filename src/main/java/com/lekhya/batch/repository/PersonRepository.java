package com.lekhya.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lekhya.batch.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}