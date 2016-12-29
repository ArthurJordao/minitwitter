package br.com.minitwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.User;

/**
 * a interface of user's crud
 * @author arthur
 *
 */

public interface UserRepository extends CrudRepository<User, String>{

}
