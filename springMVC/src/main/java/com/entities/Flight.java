package com.entities;

import java.util.Date;

import javax.persistence.*;

/**
 * Flight object worked as entity
 * 
 * @author vikantbhati
 *
 */
@Entity
public class Flight {

	@Id
	private String flightNum;
	private String depLoc;
	private String arrLoc;
	private Date validTill;
	private String flightTime;
	private double flightDuration;
	private double fare;
	private char seatAvailability;
	private char flightClass;

	/**
	 * @return the flightNum
	 */
	public String getFlightNum() {
		return flightNum;
	}

	/**
	 * @param flightNum the flightNum to set
	 */
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	/**
	 * @return the depLoc
	 */
	public String getDepLoc() {
		return depLoc;
	}

	/**
	 * @param depLoc the depLoc to set
	 */
	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}

	/**
	 * @return the arrLoc
	 */
	public String getArrLoc() {
		return arrLoc;
	}

	/**
	 * @param arrLoc the arrLoc to set
	 */
	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}

	/**
	 * @return the validTill
	 */
	public Date getValidTill() {
		return validTill;
	}

	/**
	 * @param validTill the validTill to set
	 */
	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	/**
	 * @return the flightTime
	 */
	public String getFlightTime() {
		return flightTime;
	}

	/**
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	/**
	 * @return the flightDuration
	 */
	public double getFlightDuration() {
		return flightDuration;
	}

	/**
	 * @param flightDuration the flightDuration to set
	 */
	public void setFlightDuration(double flightDuration) {
		this.flightDuration = flightDuration;
	}

	/**
	 * @return the fare
	 */
	public double getFare() {
		return fare;
	}

	/**
	 * @param fare the fare to set
	 */
	public void setFare(double fare) {
		this.fare = fare;
	}

	/**
	 * @return the seatAvailability
	 */
	public char getSeatAvailability() {
		return seatAvailability;
	}

	/**
	 * @param seatAvailability the seatAvailability to set
	 */
	public void setSeatAvailability(char seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	/**
	 * @return the classType
	 */
	public char getFlightClass() {
		return flightClass;
	}

	/**
	 * @param classType the classType to set
	 */
	public void setFlightClass(char flightClass) {
		this.flightClass = flightClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return flightNum + " " + depLoc + " " + arrLoc + " " + validTill + " " + flightTime + " " + flightDuration + " "
				+ fare + " " + seatAvailability + " " + flightClass + "\n";
	}

}
