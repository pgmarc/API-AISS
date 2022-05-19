package aiss.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"id","location", "email", "name", "address", "website", "reviews"})
public class Place {

	private Integer reviewIndex = 0;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("address")
	private String address;
	@JsonProperty("website")
	private String website;
	@JsonProperty("location")
	private Coordinates location;
	@JsonProperty("reviews")
	private Map<Integer, Review> reviews = new HashMap<Integer, Review>();
	@JsonProperty("accomodation")
	private Accomodation accomodation = null;
	
	public Place() {}

	@JsonCreator
	public Place(@JsonProperty("name") String name,
			@JsonProperty("address") String address,
			@JsonProperty("location") Coordinates location) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
	}
	
	public Place(String name, String email, String address, String website, Coordinates location) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.website = website;
		this.location = location;
	}
	
	public Place(String name, String email, String address, String website, Coordinates location, Accomodation accomodation) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.website = website;
		this.location = location;
		this.accomodation = accomodation;
	}

	public static Place create(String name, String email, String address, String website, Coordinates location) {
		return new Place(name, email, address, website, location);
	}

	public static Place create( String name, String address, Coordinates location) {
		return new Place(name, address, location);
	}
	
	public static Place create(String name, String email, String address, String website, Coordinates location, Accomodation accomodation) {
		return new Place(name, email, address, website, location, accomodation);
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
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

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}
	
	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("website")
	public String getWebsite() {
		return website;
	}

	@JsonProperty("website")
	public void setWebsite(String website) {
		this.website = website;
	}

	@JsonProperty("location")
	public Coordinates getLocation() {
		return location;
	}
	
	@JsonProperty("location")
	public void setLocation(Coordinates location) {
		this.location = location;
	}
	
	@JsonProperty("reviews")
	public Map<Integer,Review> getReviews() {
		return this.reviews;
	}
	
	@JsonProperty("accomodation")
	public Accomodation getAccomodation() {
		return this.accomodation;
	}
	
	@JsonProperty("accomodation")
	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}
	
	public static Double getDistance(Coordinates source, Coordinates target) {
		return GeographicalDistance.geographicalDistance(source, target);
	}
	
	public void addReview(Review review) {
		review.setId(reviewIndex);
		this.reviews.put(reviewIndex, review);
		reviewIndex++;
	}
	
	public Integer getNumReviews() {
		return this.reviews.size();
	}
	
	public Double getRating() {
		Double rating = 0.;
		if(!this.reviews.isEmpty()) { 
			rating = this.reviews.values().stream()
			.mapToDouble(r->r.getRating()).sum()/getNumReviews();
			rating = Math.round(rating * 10.0) /10.0;
		}
		
		return rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, location, name, accomodation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address
				+ ", website=" + website + ", location=" + location + ", accomodation=" + accomodation + "]";
	}
}
