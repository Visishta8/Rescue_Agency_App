package com.sih.rescueApp.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sih.rescueApp.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
	@Query("{'_id': ?0}")
    Optional<User> findByID(ObjectId i);

	@Query("{'User Name': ?0}")
	Optional<User> findByName(String name);
	
	@Query(value="{'_id': ?0}", delete = true)
	void deleteById(ObjectId id);

}
