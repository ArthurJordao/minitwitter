package br.com.minitwitter.model;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Tweet {
  
  @Id
  private Long id;
  
  @ManyToOne
  private User poster;
  
  @ManyToMany
  private Set<User> mentioneds;
  
  @Size(max=140)
  private String content;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getPoster() {
    return poster;
  }

  public void setPoster(User poster) {
    this.poster = poster;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Set<User> getMentioneds() {
    return Collections.unmodifiableSet(mentioneds);
  }

  public void addMentioned(User mentioned) {
    mentioneds.add(mentioned);
  }
  
}
