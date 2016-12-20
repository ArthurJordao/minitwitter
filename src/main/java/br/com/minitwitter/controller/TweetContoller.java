package br.com.minitwitter.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.minitwitter.model.Tweet;
import br.com.minitwitter.model.User;
import br.com.minitwitter.service.NotificationService;
import br.com.minitwitter.service.TweetService;
import br.com.minitwitter.service.UserService;
import br.com.minitwitter.util.TweetUtil;

@Controller
public class TweetContoller {
  
  private UserService userService;
  private TweetService tweetService;
  private NotificationService notificationService;
  
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  @Autowired
  public void setTweetService(TweetService tweetService) {
    this.tweetService = tweetService;
  }
  
  @Autowired
  public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping("/tweet")
  public String newTweet(@RequestParam("content") String content) {
    Authentication authenticated = SecurityContextHolder.getContext()
        .getAuthentication();
    Set<String> mentionedUsers = TweetUtil.getMentionedUsers(content);
    String username = authenticated.getName().toLowerCase();
    User user = new User();
    user.setUsername(username);
    Tweet tweet = new Tweet();
    tweet.setContent(content);
    tweet.setPoster(user);
    tweet.setTimeday(Calendar.getInstance());
    
    tweetService.save(tweet);
    TweetUtil.sendNotification(mentionedUsers, userService, notificationService, tweet);
    
    return "redirect:/feed";
  }


  @GetMapping("/feed")
  public String feed(Model model) {
    Authentication authenticated = SecurityContextHolder.getContext()
        .getAuthentication();
    String username = authenticated.getName().toLowerCase();
    
    User user = userService.loadUserByUsername(username);
    
    List<User> following = user.getFollowing();
    ArrayList<Tweet> feedTweets = new ArrayList<>();
    feedTweets.addAll(user.getTweets());
    
    for(User userFollowed : following) {
      List<Tweet> tweets = userFollowed.getTweets();
      feedTweets.addAll(tweets);
    }
    
    feedTweets.sort((t1,t2) -> t2.getTimeday().compareTo(t1.getTimeday()));
    model.addAttribute("tweets", feedTweets);
    model.addAttribute("content", "");
    
    return "tweets/feed";
  }
  
  @GetMapping("tweet/{id}")
  public String tweetDetails(@PathVariable("id") Long id ,Model model) {
    
    Tweet tweet = tweetService.getTweetBy(id);
    
    model.addAttribute("tweet", tweet);
    
    return "tweets/details";
  }
  
}
