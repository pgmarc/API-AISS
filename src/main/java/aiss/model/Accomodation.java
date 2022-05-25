package aiss.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import aiss.model.AccomodationPayment.PaymentPeriod;
import aiss.util.EnumValidator;

public class Accomodation {
	
	public enum AccomodationType {RESIDENCE, FLAT;
		@JsonCreator
		public static AccomodationType fromString(String key) {
			
			String newKey = key.toUpperCase();
			
			return key == null || !EnumValidator.isValidEnum(AccomodationType.class, newKey)?
					null : AccomodationType.valueOf(key.toUpperCase());
		} 
	}
	
	@JsonProperty("numberOfRooms")
	private Integer numberOfRooms;
	
	@JsonProperty("payments")
	private Map<Integer,AccomodationPayment> payments;
	
	@JsonProperty("type")
	private AccomodationType type;
	
	private Integer idCounter = 0;
	
	public Accomodation() {}

	public Accomodation(Integer roomsNumber, Map<Integer,AccomodationPayment> payments, AccomodationType type) {
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

	@JsonIgnore
	public Map<Integer,AccomodationPayment> getPayments() {
		return payments;
	}

	@JsonProperty("payments")
	public Collection<AccomodationPayment> getCollectionPayments() {
		return payments.values();
	}
	
	@JsonProperty("payment")
	public void setPayments(Map<Integer,AccomodationPayment> payments) {
		this.payments = payments;
	}
	
	public AccomodationPayment getPayment(Integer index) {
		return this.payments.get(index);
	}
	
	public void setPayment(Integer index, AccomodationPayment payment) {
		this.payments.put(index, payment);
	}
	
	public void addPayment(AccomodationPayment payment) {
		Integer id = getNewPaymentId();
		payment.setId(id);
		this.payments.put(id, payment);
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
	
	public boolean anyPaymentsMatch(Predicate<AccomodationPayment> predicate) {
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
		return "Accomodation [roomsNumber=" + numberOfRooms
				+ ", payments=" + payments + "]";
	}	
}
