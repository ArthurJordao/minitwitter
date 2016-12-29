package br.com.minitwitter.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.minitwitter.model.User;

/**
 * this class is a controller to map the indexpage
 * 
 * @author arthur
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

  /**
   * 
   * this method add a empty user to the model for register a new user
   * 
   * @param model
   * @return a view of index
   */
  @GetMapping
  public String indexPage(Model model) {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();

    if (!(auth instanceof AnonymousAuthenticationToken))
      return "forward:/feed";

    User user = new User();
    model.addAttribute("user", user);
    return "index";
  }

}
