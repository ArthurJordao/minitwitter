package br.com.minitwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.minitwitter.model.User;
import br.com.minitwitter.repository.UserRepository;

/**
 * a class with user services
 * @author arthur
 *
 */

@Repository
public class UserService implements UserDetailsService {
  
  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public User loadUserByUsername(String username)
      throws UsernameNotFoundException {
    return userRepository.findOne(username.toLowerCase());
  }
  
  public boolean contains(User user) {
    return userRepository.exists(user.getUsername());
  }

}
