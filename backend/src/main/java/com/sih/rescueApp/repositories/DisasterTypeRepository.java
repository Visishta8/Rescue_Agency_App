package com.sih.rescueApp.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sih.rescueApp.entities.DisasterType;

@Repository
public interface DisasterTypeRepository extends MongoRepository<DisasterType,Integer> {
	@Query("{'_id': ?0}")
    Optional<DisasterType> findByID(ObjectId i);

	@Query("{'Disaster Name': ?0}")
	Optional<DisasterType> findByName(String name);

}