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

import com.sih.rescueApp.entities.Activity;
import com.sih.rescueApp.services.ActivityService;

@Controller
@EnableCaching
public class ActivityController {
	@Autowired
	 private final ActivityService activityService;

	    public ActivityController(ActivityService activityService){
	        this.activityService=activityService;
	    }
	
	@PostMapping("/saveActivity")
   public ResponseEntity<String> addActivity(@RequestBody Activity activity) {
		activityService.addActivity(activity);
		activityService.updateAgency(activity.getActivityID());
       System.out.println("Activity "+activity.getActivityID()+" Added...");
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
	
	@PutMapping("/updateActivity/{id}")
   public ResponseEntity<String> updateActivity(@PathVariable ObjectId id) {
       activityService.updateActivity(id);
       System.out.println("Activity "+id+" Updated...");
       return ResponseEntity.status(HttpStatus.OK).build();
   }

   @GetMapping("/activities")
   public ResponseEntity<List<Activity>> getAll() {
   	List<Activity> activities = activityService.getAll();
       if (activities.isEmpty()) {
           return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
       }
       return ResponseEntity.ok(activities);
   }
   
   @GetMapping("/activity/{id}")
   public ResponseEntity<Activity> getByName(@PathVariable ObjectId id) {
	   Activity activity = activityService.getByID(id);
      return ResponseEntity.ok(activity);
   }

   @DeleteMapping("/deleteActivity/{id}")
   public ResponseEntity<String> deleteActivity(@PathVariable ObjectId id) {
	   activityService.deleteActivity(id);
       System.out.println("Activity "+id+" Deleted...");
       return ResponseEntity.noContent().build();
   }

}

