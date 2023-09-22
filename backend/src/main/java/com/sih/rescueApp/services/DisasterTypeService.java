package com.sih.rescueApp.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.rescueApp.entities.DisasterType;
import com.sih.rescueApp.repositories.DisasterTypeRepository;

@Service
public class DisasterTypeService {
	@Autowired
    private DisasterTypeRepository disasterTypeRepository;

    public DisasterTypeService(DisasterTypeRepository disasterTypeRepository){
        this.disasterTypeRepository=disasterTypeRepository;
    }

    public void addDisasterType(DisasterType disasterType){
        disasterTypeRepository.insert(disasterType);
    }
	
	public List<DisasterType> getAll(){
        return disasterTypeRepository.findAll();
    }

	public DisasterType getByID(ObjectId id) {
		return disasterTypeRepository.findByID(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Disaster by ID %s", id.toString())));
	}
}
