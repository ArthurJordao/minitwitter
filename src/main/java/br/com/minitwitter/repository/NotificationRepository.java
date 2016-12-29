package br.com.minitwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.minitwitter.model.Notification;

/**
 * a interface of notification's crud
 * @author arthur
 *
 */

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
