package com.sih.rescueApp.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sih.rescueApp.entities.Resource;
import com.sih.rescueApp.services.ResourceService;

@Controller
public class ResourceController {
	@Autowired
	 private final ResourceService resourceService;

	    public ResourceController(ResourceService resourceService){
	        this.resourceService=resourceService;
	    }
	
	@PostMapping("/saveResource")
   public ResponseEntity<String> addResource(@RequestBody Resource resource) {
		resourceService.addResource(resource);
       System.out.println("Resource "+resource.getResourceID()+" Added...");
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
	
	@PutMapping("/updateResource")
   public ResponseEntity<String> updateResource(@RequestBody Resource resource) {
       resourceService.updateResource(resource.getResourceID(), resource);
       System.out.println("Resource "+resource.getResourceID()+" Updated...");
       return ResponseEntity.status(HttpStatus.OK).build();
   }

   @GetMapping("/resources")
   public ResponseEntity<List<Resource>> getAll() {
   	List<Resource> resources = resourceService.getAll();
       if (resources.isEmpty()) {
           return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
       }
       return ResponseEntity.ok(resources);
   }
   
   @GetMapping("/resource/{id}")
   public ResponseEntity<Resource> getByName(@PathVariable ObjectId id) {
	   Resource resource = resourceService.getByID(id);
      return ResponseEntity.ok(resource);
   }

   @DeleteMapping("/deleteResource/{id}")
   public ResponseEntity<String> deleteResource(@PathVariable ObjectId id) {
	   resourceService.deleteResource(id);
       System.out.println("Resource "+id+" Deleted...");
       return ResponseEntity.noContent().build();
   }

}