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

import com.sih.rescueApp.entities.User;
import com.sih.rescueApp.services.UserService;

@Controller
public class UserController {
	@Autowired
	 private final UserService userService;

	    public UserController(UserService userService){
	        this.userService=userService;
	    }
	
	@PostMapping("/saveUser")
   public ResponseEntity<String> addUser(@RequestBody User user) {
		userService.addUser(user);
       System.out.println("Agency "+user.getUserID()+" Added...");
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
	
	@PutMapping("/updateUser")
   public ResponseEntity<String> updateUser(@RequestBody User user) {
       userService.updateUser(user.getUserID(), user);
       System.out.println("User "+user.getUserID()+" Updated...");
       return ResponseEntity.status(HttpStatus.OK).build();
   }

   @GetMapping("/users")
   public ResponseEntity<List<User>> getAll() {
   	List<User> users = userService.getAll();
       if (users.isEmpty()) {
           return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
       }
       return ResponseEntity.ok(users);
   }
   
   @GetMapping("/user/{id}")
   public ResponseEntity<User> getByName(@PathVariable ObjectId id) {
	   User user = userService.getByID(id);
      return ResponseEntity.ok(user);
   }

   @DeleteMapping("/deleteUser/{id}")
   public ResponseEntity<String> deleteUser(@PathVariable ObjectId id) {
	   userService.deleteUser(id);
       System.out.println("User "+id+" Deleted...");
       return ResponseEntity.noContent().build();
   }

}