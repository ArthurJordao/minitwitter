package br.com.minitwitter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, Long> {

  @Query("SELECT t from Tweet t ORDER BY t.timeday DESC")
  Iterable<Tweet> findAllOrdenedByDate();

}
