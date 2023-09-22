package com.sih.rescueApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sih.rescueApp.entities.Agency;
import com.sih.rescueApp.entities.Location;
import com.sih.rescueApp.repositories.LocationRepository;


@Service
public class LocationService {
	@Autowired
    private LocationRepository locationRepository;
	
	@Autowired
    private AgencyService agencyService;

    public LocationService(LocationRepository locationRepository){
        this.locationRepository=locationRepository;
    }

    public void addLocation(Location location){
        locationRepository.insert(location);
    }
    
    public void updateLocation(ObjectId id, Location location) {
		Location savedLocation = locationRepository.findByID(id)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find Location by ID %s", location.getAgencyID().toString())));

	    locationRepository.save(savedLocation);
	}
	
	public List<Location> getAll(){
        return locationRepository.findAll();
    }

	public Location getByID(ObjectId id) {
		return locationRepository.findByID(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Location by ID %s", id.toString())));
	}
	
	public Location getByName(String name) {
		return locationRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Location %s", name)));
	}
	
	public List<Agency> getNearby(double latitude, double longitude, double maxDistanceKm) {
	    List<Location> locations = locationRepository.find(latitude, longitude, maxDistanceKm);
	    List<ObjectId> agencyIDs = locations.stream()
	               .map(Location::getAgencyID)
	               .collect(Collectors.toList());
	       
	    List<Agency> agencyDetailsResponse = agencyService.getAgencyDetails(agencyIDs);
	    return agencyDetailsResponse;
	}
	

}