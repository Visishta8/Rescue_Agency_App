package com.sih.rescueApp.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sih.rescueApp.entities.Activity;;

@Repository
public interface ActivityRepository extends MongoRepository<Activity,Integer> {

	 
	@Query("{'_id': ?0}")
    Optional<Activity> findByID(ObjectId i);

	@Query("{'Activity Name': ?0}")
	Optional<Activity> findByName(String name);

	@Query(value="{'_id': ?0}", delete = true)
	void deleteById(ObjectId id);

}