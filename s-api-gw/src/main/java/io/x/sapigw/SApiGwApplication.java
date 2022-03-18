package io.x.sapigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SApiGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SApiGwApplication.class, args);
	}

}
