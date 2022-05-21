package aiss.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import aiss.util.PlacesUtil;


@JsonPropertyOrder({"id", "name", "address", "category", "email", "website",
	"rating", "reviews", "location", "accomodation"})
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
	@JsonProperty("category")
	private PlaceCategory category;
	
	
	public Place() {}

	private Place(String name, String address, PlaceCategory category, Coordinates location) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
		this.category = category;
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

	@JsonCreator
	public static Place create(@JsonProperty("name") String name,
			@JsonProperty("address") String address,
			@JsonProperty("category") String categoryName,
			@JsonProperty("location") Coordinates location) {
		
	
		
		PlaceCategory category = PlacesUtil.getValidPlaceCategory(categoryName);

		return new Place(name, address, category, location);
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
	
	@JsonIgnore
	public Map<Integer,Review> getReviews() {
		return this.reviews;
	}
	
	@JsonProperty("reviews")
	public Collection<Review> getCollectionReviews() {
		return this.reviews.values();
	}
	
	@JsonProperty("accomodation")
	public Accomodation getAccomodation() {
		return this.accomodation;
	}
	
	@JsonProperty("accomodation")
	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}
	
	
	@JsonProperty("category")
	public PlaceCategory getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(PlaceCategory category) {
		this.category = category;
	}
	
	@JsonIgnore
	public static List<PlaceCategory> getPlaceCategories() {
		return Arrays.asList(PlaceCategory.values());
	}
	
	
	public static Double getDistance(Coordinates source, Coordinates target) {
		return GeographicalDistance.geographicalDistance(source, target);
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

	public boolean accomodationMatches(Predicate<Accomodation> pred) {
		return this.accomodation != null && pred.test(this.getAccomodation());
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
