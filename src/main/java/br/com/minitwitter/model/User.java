package br.com.minitwitter.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class User {

  @Id
  @Size(min=3, max=12, message="Your username has to have 3-12 digits")
  private String username;

  @Size(min=8, max=16, message="Password has to have 8-16 digits")
  private String password;
  
  @OneToMany(fetch = FetchType.EAGER)
  List<Role> roles;
  
  @ManyToMany
  List<User> following;

  public List<User> getFollowing() {
    return Collections.unmodifiableList(following);
  }
  
  public void addFollowing(User user) {
    following.add(user);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

}
