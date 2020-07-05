package cl.com.walmart.testbackend;

import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import cl.com.walmart.testbackend.persistence.model.Product;

@SpringBootApplication
public class TestbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestbackendApplication.class, args);
		MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "promotions");

		TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
				.onField("brand")
				.onField("description")
				.named("brand_text_description_text")
				.build();

		MongoCollection<Document> collection = mongoOps.getCollection("products");

		collection.dropIndex("brand_text_description_text");

		mongoOps.indexOps(Product.class).ensureIndex(textIndex);
	}

}
