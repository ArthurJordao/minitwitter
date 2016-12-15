package br.com.minitwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.User;

public interface UserRepository extends CrudRepository<User, String>{

}
