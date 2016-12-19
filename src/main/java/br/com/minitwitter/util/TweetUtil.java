package br.com.minitwitter.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.minitwitter.model.Notification;
import br.com.minitwitter.model.Tweet;
import br.com.minitwitter.model.User;
import br.com.minitwitter.service.NotificationService;
import br.com.minitwitter.service.UserService;

public class TweetUtil {
  public static Set<String> getMentionedUsers(String content) {
    String pattern = "(^|[^A-Za-z0-9_-])@([A-Za-z0-9_-]+)";
    Pattern r = Pattern.compile(pattern);

    String text = content.toLowerCase();
    Matcher matcher = r.matcher(text);
    HashSet<String> mentionedUsers = new HashSet<>();
    while (matcher.find()) {
      String mentioned = matcher.group(2);
      mentionedUsers.add(mentioned);
    }
    return mentionedUsers;
  }

  public static void sendNotification(Set<String> mentionedUsers,
      UserService userService, NotificationService notificationService, Tweet tweet) {
    for (String username : mentionedUsers) {
      User user = new User();
      user.setUsername(username);
      if(!userService.contains(user))
        continue;
      user = userService.loadUserByUsername(user.getUsername());
      Notification notification = new Notification();
      notification.setDatetime(tweet.getTimeday());
      notification.setReaded(false);
      notification.setUser(user);
      notification.setContent("You was target in one tweet");
      notification.setLink("/tweet/" + tweet.getId());
      notificationService.save(notification);
    }
  }
}
