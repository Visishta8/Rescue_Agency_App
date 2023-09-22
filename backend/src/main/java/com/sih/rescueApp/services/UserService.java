package com.sih.rescueApp.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.rescueApp.entities.User;
import com.sih.rescueApp.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void addUser(User user){
        userRepository.insert(user);
    }
	
	public void updateUser(ObjectId id, User user) {
		User savedUser = userRepository.findByID(id)
	            .orElseThrow(() -> new RuntimeException(
	                    String.format("Cannot Find User by ID %s", user.getUserID().toString())));

	    userRepository.save(savedUser);
	}
	
	
	public List<User> getAll(){
        return userRepository.findAll();
    }
	

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

	public User getByID(ObjectId id) {
		return userRepository.findByID(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find User by ID %s", id.toString())));
	}
}
