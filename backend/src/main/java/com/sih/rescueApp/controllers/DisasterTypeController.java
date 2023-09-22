package com.sih.rescueApp.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sih.rescueApp.entities.DisasterType;
import com.sih.rescueApp.services.DisasterTypeService;

@Controller
public class DisasterTypeController {
	@Autowired
	 private final DisasterTypeService disasterTypeService;

	    public DisasterTypeController(DisasterTypeService disasterTypeService){
	        this.disasterTypeService=disasterTypeService;
	    }
	
	@PostMapping("/saveDisasterType")
   public ResponseEntity<String> addDisasterType(@RequestBody DisasterType disasterType) {
		disasterTypeService.addDisasterType(disasterType);
       System.out.println("Disaster "+disasterType.getDisasterID()+" Added...");
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }

   @GetMapping("/disasters")
   public ResponseEntity<List<DisasterType>> getAll() {
   	List<DisasterType> disasters = disasterTypeService.getAll();
       if (disasters.isEmpty()) {
           return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
       }
       return ResponseEntity.ok(disasters);
   }
   
   @GetMapping("/disaster/{id}")
   public ResponseEntity<DisasterType> getByName(@PathVariable ObjectId id) {
	   DisasterType disasterType = disasterTypeService.getByID(id);
      return ResponseEntity.ok(disasterType);
   }

}