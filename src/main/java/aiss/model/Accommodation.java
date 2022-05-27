package aiss.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import aiss.model.AccommodationPayment.PaymentPeriod;
import aiss.util.EnumValidator;

public class Accommodation {
	
	public enum accommodationType {RESIDENCE, FLAT;
		@JsonCreator
		public static accommodationType fromString(String key) {
			
			String newKey = key.toUpperCase();
			
			return key == null || !EnumValidator.isValidEnum(accommodationType.class, newKey)?
					null : accommodationType.valueOf(key.toUpperCase());
		} 
	}
	
	@JsonProperty("numberOfRooms")
	private Integer numberOfRooms;
	
	@JsonProperty("type")
	private accommodationType type;

	@JsonProperty("payments")
	private Map<Integer,AccommodationPayment> payments;
	
	private Integer idCounter = 0;
	
	public Accommodation() {}

	public Accommodation(Integer roomsNumber, Map<Integer,AccommodationPayment> payments, accommodationType type) {
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

	@JsonProperty("type")
	public accommodationType getType() {
		return this.type;
	}

	@JsonProperty("type")
	public void setType(accommodationType type) {
		this.type = type;
	}

	@JsonIgnore
	public Map<Integer,AccommodationPayment> getPayments() {
		return payments;
	}

	@JsonProperty("payments")
	public Collection<AccommodationPayment> getCollectionPayments() {
		return payments.values();
	}
	
	@JsonProperty("payments")
	public void setPayments(Map<Integer,AccommodationPayment> payments) {
		this.payments = payments;
	}
	
	public AccommodationPayment getPayment(Integer index) {
		return this.payments.get(index);
	}
	
	public void setPayment(Integer index, AccommodationPayment payment) {
		this.payments.put(index, payment);
	}
	
	public void addPayment(AccommodationPayment payment) {
		Integer id = getNewPaymentId();
		payment.setId(id);
		this.payments.put(id, payment);
	}
	
	public void removePayment(int index) {
		this.payments.remove(index);
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
		Accommodation other = (Accommodation) obj;
		return Objects.equals(type, other.type) && payments == other.payments
				&& Objects.equals(numberOfRooms, other.numberOfRooms);
	}

	@JsonIgnore
	public Double getMinMonthlyPrice() {
		return this.payments.entrySet().stream()
				.map(e->e.getValue())
				.map(pay->pay.getPrice() / (pay.getPaymentPeriod() == PaymentPeriod.MONTHLY ? 1 : 12))
				.min(Comparator.naturalOrder())
				.orElse(null);
	}
	
	@JsonIgnore
	public Double getMaxMonthlyPrice() {
		return this.payments.entrySet().stream()
				.map(e->e.getValue())
				.map(pay->pay.getPrice() / (pay.getPaymentPeriod() == PaymentPeriod.MONTHLY ? 1 : 12))
				.max(Comparator.naturalOrder())
				.orElse(null);
	}
	
	public boolean anyPaymentsMatch(Predicate<AccommodationPayment> predicate) {
		return this.payments.entrySet().stream()
			.anyMatch(e->predicate.test(e.getValue()));
	}
	
	private Integer getNewPaymentId() {
		int value = idCounter;
		idCounter++;
		return value;
	}
	
	@Override
	public String toString() {
		return "accommodation [roomsNumber=" + numberOfRooms
				+ ", payments=" + payments + "]";
	}	
}
