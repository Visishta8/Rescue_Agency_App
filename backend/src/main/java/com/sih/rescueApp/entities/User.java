package com.sih.rescueApp.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
	@Id
	ObjectId UserID;
	@Field("User Name")
	String userName;
	@Field("Password")
	String password;
	@Field("Role")
	String role;
	
	public ObjectId getUserID() {
		return UserID;
	}
	public void setUserID(ObjectId userID) {
		UserID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}