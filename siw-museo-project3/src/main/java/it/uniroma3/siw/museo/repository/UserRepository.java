package it.uniroma3.siw.museo.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}