package br.com.minitwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.conditionalcomments.dialect.ConditionalCommentsDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@SpringBootApplication
public class MinitwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinitwitterApplication.class, args);
	}
	
	@Bean
  public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
  }

  @Bean
  public ConditionalCommentsDialect conditionalCommentDialect() {
    return new ConditionalCommentsDialect();
  }
}
