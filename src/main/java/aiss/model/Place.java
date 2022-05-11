package aiss.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","email", "address", "rating", "website", "location"})
public class Place {
	
	private static Integer index = 0;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("address")
	private String address;
	@JsonProperty("rating")
	private Integer rating;
	@JsonProperty("website")
	private String website;
	@JsonProperty("location")
	private Coordinates location;
	
	public Place() {}

	@JsonCreator
	public Place(@JsonProperty("name") String name,
			@JsonProperty("address") String address,
			@JsonProperty("location") Coordinates location) {
		super();
		this.id = index++;
		this.name = name;
		this.address = address;
		this.location = location;
	}
	
	public Place(String name, String email, String address, Integer rating, String website, Coordinates location) {
		super();
		this.id = index++;
		this.name = name;
		this.email = email;
		this.address = address;
		this.rating = rating;
		this.website = website;
		this.location = location;
	}

	public static Place create(String name, String email, String address, Integer rating, String website, Coordinates location) {
		return new Place(name, email, address, rating, website, location);
	}

	public static Place create( String name, String address, Coordinates location) {
		return new Place(name, address, location);
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

	@JsonProperty("rating")
	public Integer getRating() {
		return rating;
	}
	
	@JsonProperty("rating")
	public void setRating(Integer rating) {
		this.rating = rating;
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
	
	public static Double getDistance(Coordinates source, Coordinates target) {
		return GeographicalDistance.geographicalDistance(source, target);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, location, name);
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
		return "Place [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", rating=" + rating
				+ ", website=" + website + ", location=" + location + "]";
	}
}
