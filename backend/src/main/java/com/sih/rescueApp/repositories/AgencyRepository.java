package com.sih.rescueApp.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sih.rescueApp.entities.Agency;

@Repository
public interface AgencyRepository extends MongoRepository<Agency,ObjectId> {
	@Query("{'_id': ?0}")
    Optional<Agency> findByID(ObjectId i);
	
	@Query("{'activityID': ?0}")
    Optional<Agency> findByActivityID(ObjectId i);

	@Query("{'agencyName': ?0}")
	Optional<Agency> findByName(String name);
	
	@Query(value="{'_id': ?0}", delete = true)
	void deleteById(ObjectId id);

	@Query("{'Expertise': ?0}")
	Optional<List<Agency>> findByExpertise(String expertise);

}
