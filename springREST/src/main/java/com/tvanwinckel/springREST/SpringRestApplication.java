package com.tvanwinckel.springREST;

import com.tvanwinckel.springREST.domain.CommentRepository;
import com.tvanwinckel.springREST.domain.PostRepository;
import com.tvanwinckel.springREST.domain.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		CommentRepository commentRepository = context.getBean(CommentRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		PostRepository postRepository = context.getBean(PostRepository.class);
		context.getBeanDefinitionNames();
	}

}
