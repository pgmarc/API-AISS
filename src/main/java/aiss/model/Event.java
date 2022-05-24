package aiss.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import aiss.util.DateDeserialization;

@JsonPropertyOrder({"id", "name", "description", "date", "contactEmail", "website",
	"price", "organizators", "transport", "type"})
public class Event {
	
	private Integer reviewIndex=0;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("date")
	@JsonDeserialize(using = DateDeserialization.class)
	private LocalDateTime date;
	@JsonProperty("contactEmail")
	private String contactEmail;
	@JsonProperty("website")
	private String website;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("organizators")
	private String organizators;
	@JsonProperty("place")
	private Place place;
	private Types type;
	private Transport transport;
	@JsonProperty("reviews")
	private Map<Integer,Review> reviews;
	public enum Types {
		MUSIC,THEATRE, ART, CULTURAL;
	};
	public enum Transport {
		BUS,TRAIN, BICYCLE, UNDERGROUND;
	};
	
	public Event(String name, Double price, LocalDateTime date,  String contactEmail, 
			 String organizators) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
		this.contactEmail = contactEmail;
		this.organizators = organizators;
	}
	
	public Event(String name, Double price, String contactEmail, String organizators) {
		super();
		this.name = name;
		this.price = price;
		this.contactEmail = contactEmail;
		this.organizators = organizators;
	}
	
	public Event() {
	}

	@JsonCreator
	public static Event createEvent(
			@JsonProperty("name") String name, 
			@JsonProperty("price") Double price, 
			@JsonProperty("date") LocalDateTime date, 
			@JsonProperty("contactEmail") String contactEmail,
			@JsonProperty("organizators") String organizators) {

		return new Event(name, price, date, contactEmail, organizators);
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("type")
	public Types getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(Types type) {
		this.type = type;
	}

	@JsonProperty("transport")
	public Transport getTransport() {
		return transport;
	}

	@JsonProperty("transport")
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}
	
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonIgnore
	public LocalDateTime getLocalDateTime() {
		return date;
	}
	
	@JsonIgnore
	public LocalDate getLocalDate() {
		return date.toLocalDate();
	}
	
	@JsonProperty("date")
	public String getFormatedDate() {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	@JsonProperty("date")
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	@JsonProperty("contactEmail")
	public String getContactEmail() {
		return contactEmail;
	}

	@JsonProperty("contactEmail")
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	@JsonProperty("website")
	public String getWebsite() {
		return website;
	}
	
	@JsonProperty("website")
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}
	
	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonProperty("organizators")
	public String getOrganizators() {
		return organizators;
	}
	
	@JsonProperty("organizators")
	public void setOrganizators(String organizators) {
		this.organizators = organizators;
	}
	
	@JsonProperty("place")
	public Place getPlace() {
		return place;
	}
	
	@JsonProperty("place")
	public void setPlace(Place place) {
		this.place = place;
	}

	@JsonIgnore
	public Map<Integer, Review> getReviews() {
		return this.reviews;
	}
	
	@JsonProperty("reviews")
	public Collection<Review> getFormatedReviews() {
		return this.reviews.values();
	}
	
	@JsonProperty("reviews")
	public void setReviews(Map<Integer,Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review) {
		review.setId(reviewIndex);
		this.reviews.put(reviewIndex, review);
		reviewIndex++;
	}
	
	@JsonIgnore
	public Integer getNumReviews() {
		return this.reviews.size();
	}
	
	@JsonProperty("rating")
	public Double getRating() {
		return getFormatedReviews().stream().mapToDouble(r->r.getRating()).sum()/getNumReviews();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, name, organizators, website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(date, other.date) && Objects.equals(name, other.name)
				&& Objects.equals(organizators, other.organizators) && Objects.equals(website, other.website);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", email="
				+ contactEmail + ", website=" + website + "price=" + price + ", organizators="
				+ organizators + "]";
	}
}
