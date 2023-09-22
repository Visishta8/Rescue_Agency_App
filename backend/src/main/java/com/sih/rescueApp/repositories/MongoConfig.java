package com.sih.rescueApp.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    // Define MongoDB connection details (host and port)
    private final String mongoHost = "localhost";
    private final int mongoPort = 27017;
    private final String databaseName = "RescueDB"; // Replace with your actual database name

    @Bean
    public MongoClient mongoClient() {
        // Create a MongoClient with the MongoDB server host and port
        return MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        // Create a MongoTemplate using the MongoClient and database name
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient(), databaseName));
    }
}
