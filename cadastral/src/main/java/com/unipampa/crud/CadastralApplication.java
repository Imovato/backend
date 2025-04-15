package com.unipampa.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CadastralApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastralApplication.class, args);
	}

}
