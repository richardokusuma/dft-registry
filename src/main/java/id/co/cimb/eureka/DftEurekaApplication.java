package id.co.cimb.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer
public class DftEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DftEurekaApplication.class, args);
	}
}