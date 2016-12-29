package br.com.minitwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.minitwitter.model.Tweet;
import br.com.minitwitter.repository.TweetRepository;

/**
 * a class with role services
 * @author arthur
 *
 */

@Repository
public class TweetService {

  private TweetRepository tweetRepository;
  
  @Autowired
  public void setTweetRepository(TweetRepository tweetRepository) {
    this.tweetRepository = tweetRepository;
  }

  public void save(Tweet tweet) {
    tweetRepository.save(tweet);
  }

  public Tweet getTweetBy(Long id) {
    return tweetRepository.findOne(id);
  }

  public Iterable<Tweet> allTweets() {
    return tweetRepository.findAllOrdenedByDate();
  }
  
}
