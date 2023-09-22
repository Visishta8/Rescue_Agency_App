package com.sih.rescueApp.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sih.rescueApp.entities.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, Integer> {
    @Query("{'_id': ?0}")
    Optional<Location> findByID(ObjectId i);

    @Query("{'Location Name': ?0}")
    Optional<Location> findByName(String name);

    @Query("{'Coordinate': { $geoNear: { $geometry: { type: 'Point', coordinates: [?0, ?1] }, $maxDistance: ?2 } } }")
    List<Location> find(double x, double y, double minDistanceKm);

}

