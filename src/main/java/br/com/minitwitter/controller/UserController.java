package br.com.minitwitter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.minitwitter.model.Role;
import br.com.minitwitter.model.Tweet;
import br.com.minitwitter.model.User;
import br.com.minitwitter.service.RoleService;
import br.com.minitwitter.service.UserService;

@Controller
public class UserController {
  
  private UserService userService;
  private RoleService roleService;
  
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  @Autowired
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping("/user")
  public String createUser(@Valid @ModelAttribute User user, BindingResult br) {
    if (userService.contains(user))
      br.rejectValue("username", "error.user", "There are a user with this username.");
    if(br.hasErrors())
      return "users/form";
    Role role = new Role();
    
    role.setRole("ROLE_NORMAL");
    
    if(!roleService.hasRole(role.getAuthority())){
      roleService.save(role);
    }
    
    user.addRole(role);
    
    user.setUsername(user.getUsername().toLowerCase());
    
    userService.save(user);
    
    
    return "redirect:/";
  }
  
  @GetMapping("/user")
  public String userForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    
    return "users/form";
    
  }
  
  @GetMapping("/login")
  public String loginForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    
    
    return "users/login";
  }
  
  @GetMapping("/{username}")
  public String profileDetails(@PathVariable("username") String username, Model model) {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String currentUsername = auth.getName().toLowerCase();
    username = username.toLowerCase();
    User currentUser = new User();
    currentUser.setUsername(currentUsername);
    boolean containsUser = userService.contains(currentUser);
    if (!containsUser)
      return "redirect:/";
    if(currentUsername.equals(username))
      model.addAttribute("isOwner", true);
    else
      model.addAttribute("isOwner", false);
    
    User user = userService.loadUserByUsername(username.toLowerCase());
    
    if(containsUser){
      currentUser = userService.loadUserByUsername(currentUsername);
      boolean isFollowing = currentUser.isFollowing(user);
      model.addAttribute("isFollowing", isFollowing);
      
    } else {
      model.addAttribute("isFollowing", false);
    }
    
    List<Tweet> tweets = user.getTweets();
    
    model.addAttribute("tweets", tweets);
    
    model.addAttribute("user", user);
    
    model.addAttribute("following", user.getFollowing().size());
    
    model.addAttribute("followers", user.getFollowers().size());
    
    return "users/details";
  }
  
  @PostMapping("/follow")
  public String follow(@RequestParam("username") String username) {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String currentUsername = auth.getName().toLowerCase();
    if(currentUsername.equals(username))
      return "redirect:/" + username;
    
    User user = userService.loadUserByUsername(username);
    User currentUser = userService.loadUserByUsername(currentUsername);
    
    if(!currentUser.isFollowing(user))
      currentUser.addFollowing(user);
    userService.save(currentUser);
    
    
    return "redirect:/" + username;
  }

  @PostMapping("/unfollow")
  public String unfollow(@RequestParam("username") String username) {
    
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String currentUsername = auth.getName().toLowerCase();
    if(currentUsername.equals(username))
      return "redirect:/" + username;
    
    User user = userService.loadUserByUsername(username);
    User currentUser = userService.loadUserByUsername(currentUsername);
    
    if(currentUser.isFollowing(user))
      currentUser.removeFollowing(user);
    
    userService.save(currentUser);
    
    return "redirect:/" + username;
  }
}
