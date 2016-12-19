package br.com.minitwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
