package com.sih.rescueApp.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sih.rescueApp.entities.Agency;
import com.sih.rescueApp.services.AgencyService;

@Controller
@EnableCaching
public class AgencyController {
	@Autowired
	 private final AgencyService agencyService;

	    public AgencyController(AgencyService agencyService){
	        this.agencyService=agencyService;
	    }
	
	@PostMapping("/saveAgency")
   public ResponseEntity<String> addAgency(@RequestBody Agency agency) {
		agencyService.addAgency(agency);
       System.out.println("Agency "+agency.getAgencyID()+" Added...");
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
	
	@PutMapping("/updateAgency")
   public ResponseEntity<String> updateAgency(@RequestBody Agency agency) {
       agencyService.updateAgency(agency.getAgencyID(), agency);
       System.out.println("Agency "+agency.getAgencyID()+" Updated...");
       return ResponseEntity.status(HttpStatus.OK).build();
   }
	
	@PutMapping("/updateStatus")
	   public ResponseEntity<String> updateStatus(ObjectId agencyID, ObjectId activityID) {
	       agencyService.updateStatus(agencyID, activityID);
	       System.out.println("Agency Updated...");
	       return ResponseEntity.status(HttpStatus.OK).build();
	   }

   @GetMapping("/agencies")
   //@Cacheable(key="#id", value="agency")
   public ResponseEntity<List<Agency>> getAll() {
   	List<Agency> agencies = agencyService.getAll();
       if (agencies.isEmpty()) {
           return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
       }
       return ResponseEntity.ok(agencies);
   }
   
   @GetMapping("/agency/{id}")
   public ResponseEntity<Agency> getByID(@PathVariable ObjectId id) {
	   Agency agency = agencyService.getByID(id);
      return ResponseEntity.ok(agency);
   }
   
   @GetMapping("/agencyExp/{expertise}")
   public ResponseEntity<List<Agency>> getByExpertise(@PathVariable String expertise) {
	   List<Agency> agencies = agencyService.getByExpertise(expertise);
	   if (agencies.isEmpty()) {
           return ResponseEntity.noContent().build(); 
       }
       return ResponseEntity.ok(agencies);
   }

   @DeleteMapping("/deleteAgency/{id}")
   //@CacheEvict(key="#id", value="agency")
   public ResponseEntity<String> deleteAgency(@PathVariable ObjectId id) {
	   agencyService.deleteAgency(id);
       System.out.println("Agency "+id+" Deleted...");
       return ResponseEntity.noContent().build();
   }
}
