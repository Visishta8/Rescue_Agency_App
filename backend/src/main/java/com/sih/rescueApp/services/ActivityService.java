package com.sih.rescueApp.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.rescueApp.entities.Activity;
import com.sih.rescueApp.entities.Agency;
import com.sih.rescueApp.repositories.ActivityRepository;

@Service
public class ActivityService {
	@Autowired
    private ActivityRepository activityRepository;
	@Autowired
	private LocationService locationService ;
	@Autowired
	private AgencyService agencyService ;

    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository=activityRepository;
    }
    
/*
    public void addActivity(Activity activity) {
        int people = activity.getPeople();
        String details = activity.getDetails();

        String classification;

        if (people <= 10 && details.equals("Shelter, Food")) {
            classification = "Simple";
        } else if (people > 10 && people <= 50 && details.equals("Shelter, Food")) {
            classification = "Simple";
        } else if (people <= 10 && details.contains("Medical Care")) {
            classification = "Urgent";
        } else if (people > 10 && people <= 50 && details.contains("Medical Care")) {
            classification = "Critical";
        } else {
            classification = "Critical";
        }

        List<Agency> nearbyLocations = locationService.getNearby(activity.getCoordinate().getX(), activity.getCoordinate().getY(), 1000);
        Agency nearestAvailableAgency = null;

        for (Agency agency : nearbyLocations) {
            if (agency.getStatus().equals(false) && agency.getAgencyID().equals(null)) {
                // Check if the agency is already assigned to another activity
                    nearestAvailableAgency = agency;
                    break;
            }
        }

        if (nearestAvailableAgency != null) {
            activity.setAgencyID(nearestAvailableAgency.getAgencyID());
            /*nearestAvailableAgency.setStatus(true);
            nearestAvailableAgency.setActivityID(activity.getActivityID());
        }
        System.out.println(activity.getAgencyID());
        activity.setActivityType(classification);
        activityRepository.save(activity);
    }
*/
    
    public void addActivity(Activity activity) {
        int people = activity.getPeople();
        String details = activity.getDetails();

        String classification;

        if (people<=10 && details.equals("Shelter, Food")) {
            classification = "Simple";
        } else if (people>10 && people<= 50 && details.equals("Shelter, Food")) {
            classification = "Simple";
        } else if (people<=10 && details.contains("Medical Care")) {
            classification = "Urgent";
        } else if (people>10 && people<= 50 && details.contains("Medical Care")) {
            classification = "Critical";
        } else {
            classification = "Critical";
        }
        
        //if(classification=="Simple") {
	        List<Agency> nearbyLocations = locationService.getNearby(activity.getCoordinate().getX(), activity.getCoordinate().getY(),1000000000);
	        for (Agency agency : nearbyLocations) {
	            if (agency.getStatus().equals(false)) {
	            	activity.setAgencyID(agency.getAgencyID());
	            	//agencyService.updateStatus(agency.getAgencyID(),activity.getActivityID());
	            	break;
	            }
	        }
        /*} else if(classification=="Urgent") {
        	List<Agency> nearbyLocations = locationController.getNearby(activity.getCoordinate());
	        for (Agency agency : nearbyLocations) {
	            if (agency.getStatus().equals(false)) {
	            	activity.setAgencyID(agency.getAgencyID());
	            	agencyController.updateStatus(activity.getActivityID());
	            }
	        }
        }*/
                
        activity.setActivityType(classification);
        activityRepository.save(activity);
    }
    public void updateAgency(ObjectId id) {
    	Activity savedActivity = activityRepository.findByID(id)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find Agency with ID %s", id.toString())));
    	
    	agencyService.updateStatus(savedActivity.getAgencyID(),savedActivity.getActivityID());
    	savedActivity.setProgress("In Progress");
    	activityRepository.save(savedActivity);
    	
    }
      
    public void updateActivity(ObjectId id) {
    	Activity savedActivity = activityRepository.findByID(id)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find Activity with ID %s", id.toString())));
    	
    	agencyService.updateStatus(savedActivity.getAgencyID(),savedActivity.getActivityID());
    	savedActivity.setProgress("Done");
    	activityRepository.save(savedActivity);
    	
    }
      
	
	public List<Activity> getAll(){
        return activityRepository.findAll();
    }

    public void deleteActivity(ObjectId id){
        activityRepository.deleteById(id);
    }

	public Activity getByID(ObjectId id) {
		return activityRepository.findByID(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Activity by ID %s", id.toString())));
	}

}
