package br.com.minitwitter.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Notification {
  
  @Id
  private Long id;
  
  private String content;
  
  private Boolean read;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar datetime;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

}
