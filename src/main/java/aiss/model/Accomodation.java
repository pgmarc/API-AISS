package aiss.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Accomodation {
	
	public enum AccomodationType {RESIDENCE, FLAT}
	
	@JsonProperty("numberOfRooms")
	private Integer numberOfRooms;
	
	@JsonProperty("payments")
	private List<AccomodationPayment> payments;
	
	@JsonProperty("type")
	private AccomodationType type;
	
	public Accomodation() {}

	public Accomodation(Integer id, Integer roomsNumber, Double price, List<AccomodationPayment> payments, AccomodationType type) {
		this.numberOfRooms = roomsNumber;
		this.payments = payments;
		this.type = type;
		}

	@JsonProperty("numberOfRooms")
	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}
	
	@JsonProperty("numberOfRooms")
	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	@JsonProperty("payments")
	public List<AccomodationPayment> getPayments() {
		return payments;
	}

	@JsonProperty("payment")
	public void setPayments(List<AccomodationPayment> payments) {
		this.payments = payments;
	}
	
	public void getPayment(Integer index) {
		this.payments.get(index);
	}
	
	public void addPayment(AccomodationPayment payment) {
		this.payments.add(payment);
	}
	
	public void removePayment(int index) {
		this.payments.remove(index);
	}
	
	
	@JsonProperty("type")
	public AccomodationType getType() {
		return this.type;
	}
	
	@JsonProperty("type")
	public void setType(AccomodationType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(payments, numberOfRooms, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accomodation other = (Accomodation) obj;
		return Objects.equals(type, other.type) && payments == other.payments
				&& Objects.equals(numberOfRooms, other.numberOfRooms);
	}

	@Override
	public String toString() {
		return "Accomodation [roomsNumber=" + numberOfRooms
				+ ", payments=" + payments + "]";
	}
}