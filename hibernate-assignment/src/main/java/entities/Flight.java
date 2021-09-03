package entities;

import java.util.Date;

import javax.persistence.*;

/**
 * Flight object worked as entity
 * @author vikantbhati
 *
 */
@Entity
@Table(name="flights")
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
	 * default constructor
	 */
	public Flight() {
		
	}

	/**
	 * 
	 * @param flightNum
	 * @param depLoc
	 * @param arrLoc
	 * @param validTill
	 * @param flightTime
	 * @param flightDuration
	 * @param fare
	 * @param seatAvailability
	 * @param flightClass
	 */
	public Flight(String flightNum, String depLoc, String arrLoc, Date validTill, String flightTime,
			double flightDuration, double fare, char seatAvailability, char flightClass) {
		super();
		this.flightNum = flightNum;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.fare = fare;
		this.seatAvailability = seatAvailability;
		this.flightClass = flightClass;
	}

	/**
	 * it is call when a searched Flight object is created
	 * @param depLoc
	 * @param arrLoc
	 * @param validTill
	 * @param flightClass
	 */
	public Flight(String depLoc, String arrLoc, Date validTill, char flightClass) {
		super();
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;
		this.flightClass = flightClass;
	}

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
				+ fare + " " + seatAvailability + " " + flightClass+"\n";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrLoc == null) ? 0 : arrLoc.hashCode());
		result = prime * result + ((depLoc == null) ? 0 : depLoc.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fare);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + flightClass;
		temp = Double.doubleToLongBits(flightDuration);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((flightNum == null) ? 0 : flightNum.hashCode());
		result = prime * result + ((flightTime == null) ? 0 : flightTime.hashCode());
		result = prime * result + seatAvailability;
		result = prime * result + ((validTill == null) ? 0 : validTill.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (arrLoc == null) {
			if (other.arrLoc != null)
				return false;
		} else if (!arrLoc.equals(other.arrLoc))
			return false;
		if (depLoc == null) {
			if (other.depLoc != null)
				return false;
		} else if (!depLoc.equals(other.depLoc))
			return false;
		if (Double.doubleToLongBits(fare) != Double.doubleToLongBits(other.fare))
			return false;
		if (flightClass != other.flightClass)
			return false;
		if (Double.doubleToLongBits(flightDuration) != Double.doubleToLongBits(other.flightDuration))
			return false;
		if (flightNum == null) {
			if (other.flightNum != null)
				return false;
		} else if (!flightNum.equals(other.flightNum))
			return false;
		if (flightTime == null) {
			if (other.flightTime != null)
				return false;
		} else if (!flightTime.equals(other.flightTime))
			return false;
		if (seatAvailability != other.seatAvailability)
			return false;
		if (validTill == null) {
			if (other.validTill != null)
				return false;
		} else if (!validTill.equals(other.validTill))
			return false;
		return true;
	}

}
