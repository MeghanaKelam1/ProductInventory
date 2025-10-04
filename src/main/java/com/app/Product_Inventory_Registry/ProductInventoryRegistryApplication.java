package com.app.Product_Inventory_Registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProductInventoryRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInventoryRegistryApplication.class, args);
	}

}
