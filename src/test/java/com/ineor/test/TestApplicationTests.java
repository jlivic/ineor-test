package com.ineor.test;

import com.ineor.test.jsonEntity.Header;
import com.ineor.test.logic.BusinessLogic;
import com.sun.net.httpserver.Authenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads(){
	}

	@Test
	public void doJob() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		BusinessLogic.doJob(restTemplate.getForObject("http://jsonvat.com/", Header.class));
		assertThat(restTemplate.getForObject("http://jsonvat.com/", Header.class)).isNotNull();

	}

}
