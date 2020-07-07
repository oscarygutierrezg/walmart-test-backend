package cl.com.walmart.testbackend.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "cl.com.walmart.testbackend.persistence.repository")
@ComponentScan(basePackages = {"cl.com.walmart.testbackend"})
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
		return "promotions";
	}

	public @Bean MongoClient mongoClient() {
		return MongoClients.create("mongodb://mongodb:27017");
	}



}
