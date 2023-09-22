package com.sih.rescueApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.rescueApp.entities.Activity;
import com.sih.rescueApp.entities.Agency;
import com.sih.rescueApp.repositories.ActivityRepository;
import com.sih.rescueApp.repositories.AgencyRepository;

@Service
public class AgencyService {
	@Autowired
    private AgencyRepository agencyRepository;
	@Autowired
    private ActivityRepository activityRepository;
	@Autowired
	private final ActivityService activityService;

    public AgencyService(AgencyRepository agencyRepository, ActivityService activityService){
        this.agencyRepository=agencyRepository;
        this.activityService=activityService;
    }

    public void addAgency(Agency agency){
        agencyRepository.insert(agency);
    }

	public void updateAgency(ObjectId id, Agency agency) {
		Agency savedAgency = agencyRepository.findByID(id)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find Agency by ID %s", agency.getAgencyID().toString())));

	    agencyRepository.save(savedAgency);
	}
	
	public List<Agency> getAll(){
        return agencyRepository.findAll();
    }
	
    public void deleteAgency(ObjectId id){
        agencyRepository.deleteById(id);
    }

	public Agency getByID(ObjectId id) {
		return agencyRepository.findByID(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Agency by ID %s", id.toString())));
	}

	public List<Agency> getByExpertise(String expertise) {
		return agencyRepository.findByExpertise(expertise).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Agencies nearby with Expertise in %s", expertise)));
		
	}

	public void updateStatus(ObjectId agencyID, ObjectId activityID) {
		System.out.println("Updating agency status for agencyID: " + agencyID + " and activityID: " + activityID);
		Agency savedAgency = agencyRepository.findByID(agencyID)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find Agency")));
		Activity activity=activityService.getByID(activityID);
		if(activity.getProgress().equals("None")) {
			savedAgency.setStatus(true);
			savedAgency.setActivityID(activityID);
			System.out.println("Agency status updated to true.");
		}
		else if(activity.getProgress().equals("In Progress")){
			savedAgency.setStatus(false);
			savedAgency.setActivityID(null);
			activity.setProgress("Done");
		}
		agencyRepository.save(savedAgency);
		activityRepository.save(activity);
		System.out.println("Agency saved with updated status.");
	}
	
	public List<Agency> getAgencyDetails(List<ObjectId> agencyIDs) {
        List<Agency> agencyDetails = new ArrayList<>();
        
        for (ObjectId agencyID : agencyIDs) {
            Optional<Agency> optionalAgency = agencyRepository.findByID(agencyID);
            optionalAgency.ifPresent(agencyDetails::add);
        }
        
        return agencyDetails;
    }
}
