package aiss.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccomodationPayment {
	
	public enum PaymentPeriod {YEARLY, MONTHLY}
	public enum MealService {ALL_MEALS,TWO_MEALS,SELF_CATERING}
	public enum RoomType {INDIVIDUAL, DOUBLE, NUMEROUS}
	
	@JsonProperty("description")
	private String description;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("paymentPeriod")
	private PaymentPeriod paymentPeriod;
	@JsonProperty("mealService")
	private MealService mealService;
	@JsonProperty("roomType")
	private RoomType roomType;
	
	public AccomodationPayment() {}
	
	public AccomodationPayment(String description, Double price, PaymentPeriod paymentPeriod, MealService mealService, RoomType roomType) {
		this.description = description;
		this.price = price;
		this.paymentPeriod = paymentPeriod;
		this.mealService = mealService;
		this.roomType = roomType;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}
	
	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonProperty("paymentPeriod")
	public PaymentPeriod getPaymentPeriod() {
		return paymentPeriod;
	}
	
	@JsonProperty("paymentPeriod")
	public void setPaymentPeriod(PaymentPeriod paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}
	
	@JsonProperty("mealService")
	public MealService getMealService() {
		return mealService;
	}
	
	@JsonProperty("mealService")
	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}
	
	@JsonProperty("roomType")
	public RoomType getRoomType() {
		return roomType;
	}
	
	@JsonProperty("roomType")
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(description, mealService, paymentPeriod, price);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccomodationPayment other = (AccomodationPayment) obj;
		return Objects.equals(description, other.description) && mealService == other.mealService
				&& paymentPeriod == other.paymentPeriod && Objects.equals(price, other.price);
	}
	
	@Override
	public String toString() {
		return "AccomodationPayment [description=" + description + ", price=" + price + ", paymentPeriod="
				+ paymentPeriod + ", mealService=" + mealService + "]";
	}

}
