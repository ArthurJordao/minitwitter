package br.com.minitwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, Long> {

}
