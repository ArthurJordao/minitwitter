package br.com.minitwitter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
  
  @Id
  private String role;

}
