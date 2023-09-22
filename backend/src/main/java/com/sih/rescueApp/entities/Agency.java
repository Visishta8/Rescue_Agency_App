package com.sih.rescueApp.entities;

import java.io.Serializable;

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
@Document(collection = "Agency")
public class Agency implements Serializable{
	@Id
	ObjectId agencyID;
	@Field("Agency Name")
	String agencyName;
	@Field("Agency E-Mail")
	String agencyMail;
	@Field("Phone No.")
	String phone;
	@Field("Activity ID")
	ObjectId activityID;
	@Field("Expertise")
	String expertise;
	@Field("Busy")
	Boolean status = false;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public ObjectId getActivityID() {
		return activityID;
	}
	public void setActivityID(ObjectId activityID) {
		this.activityID = activityID;
	}
	public ObjectId getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(ObjectId agencyID) {
		this.agencyID = agencyID;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyMail() {
		return agencyMail;
	}
	public void setAgencyMail(String agencyMail) {
		this.agencyMail = agencyMail;
	}
	public String getPhone() {
		return phone;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
