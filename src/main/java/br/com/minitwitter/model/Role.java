package br.com.minitwitter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 * a model class to roles
 * @author arthur
 *
 */

@Entity
public class Role implements GrantedAuthority {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Id
  private String role;

  @Override
  public String getAuthority() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
