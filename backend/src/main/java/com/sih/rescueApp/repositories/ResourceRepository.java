package com.sih.rescueApp.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sih.rescueApp.entities.Resource;

@Repository
public interface ResourceRepository extends MongoRepository<Resource,Integer> {
	@Query("{'_id': ?0}")
    Optional<Resource> findByID(ObjectId i);

	@Query("{'Resource Name': ?0}")
	Optional<Resource> findByName(String name);
	
	@Query(value="{'_id': ?0}", delete = true)
	void deleteById(ObjectId id);

}
