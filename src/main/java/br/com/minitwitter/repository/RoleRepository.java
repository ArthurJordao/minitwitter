package br.com.minitwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.Role;

/**
 * a interface of role's crud
 * @author arthur
 *
 */

public interface RoleRepository extends CrudRepository<Role, String>{

}
