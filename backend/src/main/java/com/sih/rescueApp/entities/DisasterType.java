package com.sih.rescueApp.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "DisasterType")
public class DisasterType {
	@Id
	ObjectId disasterID;
	@Field("Disaster Name")
	String disasterName;
	
	public ObjectId getDisasterID() {
		return disasterID;
	}
	public void setDisasterID(ObjectId disasterID) {
		this.disasterID = disasterID;
	}
	public String getDisasterName() {
		return disasterName;
	}
	public void setDisasterName(String disasterName) {
		this.disasterName = disasterName;
	}
	
	
}
