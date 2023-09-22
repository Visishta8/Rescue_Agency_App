package com.sih.rescueApp.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Activity")
public class Activity implements Serializable{
	@Id
	ObjectId activityID;
	ObjectId agencyID;
	@Field("Activity Type")
	String activityType;
	@Field("Activity Service")
	String details;
	@Field("People Affected")
	int people;
	@Field("Coordinate")
	Point coordinate;
	@Field("Progress")
	String progress = "None";
	
	
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public Point getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
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
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
