package br.com.minitwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.minitwitter.model.Role;
import br.com.minitwitter.repository.RoleRepository;

/**
 * a class with role services
 * @author arthur
 *
 */

@Repository
public class RoleService {
  
  private RoleRepository roleRepository;

  @Autowired
  public void setRoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }
  
  public void save(Role role) {
    roleRepository.save(role);
  }
  
  public boolean hasRole(String role) {
    return roleRepository.exists(role);
  }

}
