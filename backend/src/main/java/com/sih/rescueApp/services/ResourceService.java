package com.sih.rescueApp.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.rescueApp.entities.Resource;
import com.sih.rescueApp.repositories.ResourceRepository;

@Service
public class ResourceService {
	@Autowired
    private ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository){
        this.resourceRepository=resourceRepository;
    }

    public void addResource(Resource resource){
        resourceRepository.insert(resource);
    }
	
	public void updateResource(ObjectId id, Resource resource) {
		Resource savedResource = resourceRepository.findByID(id)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find Resource by ID %s", resource.getResourceID().toString())));

	    resourceRepository.save(savedResource);
	}
	
	
	public List<Resource> getAll(){
        return resourceRepository.findAll();
    }
	

    public void deleteResource(ObjectId id){
        resourceRepository.deleteById(id);
    }

	public Resource getByID(ObjectId id) {
		return resourceRepository.findByID(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Resource by ID %s", id.toString())));
	}
}
