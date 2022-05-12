package aiss.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Accomodation {
	
	@JsonProperty("roomsNumber")
	private Integer roomsNumber;
	
	@JsonProperty("area")
	private Double area;
	
	@JsonProperty("payments")
	private List<AccomodationPayment> payments;
	
	
	public Accomodation() {}

	public Accomodation(Integer id, Integer roomsNumber, Double area, Double price, List<AccomodationPayment> payments) {
		this.roomsNumber = roomsNumber;
		this.area = area;
		this.payments = payments;
	}

	@JsonProperty("roomsNumber")
	public Integer getRoomsNumber() {
		return roomsNumber;
	}
	
	@JsonProperty("roomsNumber")
	public void setRoomsNumber(Integer roomsNumber) {
		this.roomsNumber = roomsNumber;
	}
	
	@JsonProperty("area")
	public Double getArea() {
		return area;
	}

	@JsonProperty("area")
	public void setArea(Double area) {
		this.area = area;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(area, payments, roomsNumber);
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
		return Objects.equals(area, other.area) && payments == other.payments
				&& Objects.equals(roomsNumber, other.roomsNumber);
	}

	@Override
	public String toString() {
		return "Accomodation [roomsNumber=" + roomsNumber + ", area=" + area
				+ ", payments=" + payments + "]";
	}
}
