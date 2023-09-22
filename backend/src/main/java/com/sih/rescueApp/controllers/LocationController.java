package com.sih.rescueApp.controllers;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sih.rescueApp.entities.Agency;
import com.sih.rescueApp.entities.Location;
import com.sih.rescueApp.services.LocationService;


@Controller
@EnableCaching
public class LocationController {
	@Autowired
	 private final LocationService locationService;

	    public LocationController(LocationService locationService){
	        this.locationService=locationService;
	    }
	
	@PostMapping("/saveLocation")
   public ResponseEntity<String> addLocation(@RequestBody Location location) {
		locationService.addLocation(location);
       System.out.println("Location "+location.getLocationID()+" Added...");
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
	
	@PutMapping("/updateLocation")
	   public ResponseEntity<String> updateAgency(@RequestBody Location location) {
	       locationService.updateLocation(location.getLocationID(), location);
	       System.out.println("Location "+location.getLocationID()+" Updated...");
	       return ResponseEntity.status(HttpStatus.OK).build();
	   }

   @GetMapping("/locations")
   public ResponseEntity<List<Location>> getAll() {
   	List<Location> locations = locationService.getAll();
       if (locations.isEmpty()) {
           return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
       }
       return ResponseEntity.ok(locations);
   }
   
   @GetMapping("/nearby")
   @ResponseBody
   public List<Agency> getNearby(@RequestParam("Latitude") double latitude, @RequestParam("Longitude") double longitude) {
       List<Agency> agencies = locationService.getNearby(latitude, longitude, 1000);
       
       if (agencies.isEmpty()) {
           return Collections.emptyList(); // Return 204 No Content if the list is empty
       }
       return agencies;
   }

   
   @GetMapping("/location/{id}")
   public ResponseEntity<Location> getByName(@PathVariable ObjectId id) {
	   Location location = locationService.getByID(id);
      return ResponseEntity.ok(location);
   }

}
