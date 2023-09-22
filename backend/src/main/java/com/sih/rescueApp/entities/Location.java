package com.sih.rescueApp.entities;


import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
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
@Document(collection = "Location")
public class Location implements Serializable{
	@Id
	ObjectId locationID;
	@Field("Agency ID")
	ObjectId agencyID;
	@Field("Coordinate")
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	Point Coordinate;
	@Field("Location Name")
	String LocationName;
	@Field("Location Type")
	String LocationType;
	
	public ObjectId getLocationID() {
		return locationID;
	}
	public void setLocationID(ObjectId locationID) {
		this.locationID = locationID;
	}
	public ObjectId getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(ObjectId agencyID) {
		this.agencyID = agencyID;
	}
	public Point getCoordinate() {
		return Coordinate;
	}
	public void setCoordinate(Point coordinate) {
		Coordinate = coordinate;
	}
	public String getLocationName() {
		return LocationName;
	}
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
	public String getLocationType() {
		return LocationType;
	}
	public void setLocationType(String locationType) {
		LocationType = locationType;
	}
	
	
}
