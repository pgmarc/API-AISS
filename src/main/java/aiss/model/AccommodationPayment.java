package aiss.model;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

import aiss.util.EnumValidator;

public class AccommodationPayment {
	
	public enum PaymentPeriod {YEARLY, MONTHLY;
		@JsonCreator
		public static PaymentPeriod fromString(String key) {
			
			String newKey = key.toUpperCase();
			
			return key == null || !EnumValidator.isValidEnum(PaymentPeriod.class, newKey)?
					null : PaymentPeriod.valueOf(key.toUpperCase());
		} 
	}
	public enum MealService {ALL_MEALS,TWO_MEALS,SELF_CATERING;
		@JsonCreator
		public static MealService fromString(String key) {
			
			String newKey = key.toUpperCase();
			
			return key == null || !EnumValidator.isValidEnum(MealService.class, newKey)?
					null : MealService.valueOf(key.toUpperCase());
		} 
	}
	public enum RoomType {INDIVIDUAL, DOUBLE, OTHER;
		@JsonCreator
		public static RoomType fromString(String key) {
			
			String newKey = key.toUpperCase();
			
			return key == null || !EnumValidator.isValidEnum(RoomType.class, newKey)?
					null : RoomType.valueOf(key.toUpperCase());
		} 
	}
	
	@JsonProperty("id")
	private Integer id;
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
	
	public AccommodationPayment() {}
	
	public AccommodationPayment(String description, Double price, PaymentPeriod paymentPeriod, MealService mealService, RoomType roomType) {
		this.description = description;
		this.price = price;
		this.paymentPeriod = paymentPeriod;
		this.mealService = mealService;
		this.roomType = roomType;
	}

	@JsonProperty("id") 
	public Integer getId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
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
		AccommodationPayment other = (AccommodationPayment) obj;
		return Objects.equals(description, other.description) && mealService == other.mealService
				&& paymentPeriod == other.paymentPeriod && Objects.equals(price, other.price);
	}
	
	@Override
	public String toString() {
		return "accommodationPayment [description=" + description + ", price=" + price + ", paymentPeriod="
				+ paymentPeriod + ", mealService=" + mealService + "]";
	}
}
