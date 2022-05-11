package aiss.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Accomodation {
	private enum PaymentType {YEARLY,MONTHLY};
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("roomsNumber")
	private Integer roomsNumber;
	
	@JsonProperty("area")
	private Double area;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("payment")
	private PaymentType payment;
	
	
	public Accomodation() {}

	public Accomodation(Integer id, Integer roomsNumber, Double area, Double price, PaymentType payment) {
		this.id = id;
		this.roomsNumber = roomsNumber;
		this.area = area;
		this.price = price;
		this.payment = payment;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
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

	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonProperty("payment")
	public PaymentType getPayment() {
		return payment;
	}

	@JsonProperty("payment")
	public void setPayment(PaymentType payment) {
		this.payment = payment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, payment, price, roomsNumber);
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
		return Objects.equals(area, other.area) && payment == other.payment && Objects.equals(price, other.price)
				&& Objects.equals(roomsNumber, other.roomsNumber);
	}

	@Override
	public String toString() {
		return "Accomodation [id=" + id + ", roomsNumber=" + roomsNumber + ", area=" + area + ", price=" + price
				+ ", payment=" + payment + "]";
	}
}
