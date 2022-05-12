ppackage aiss.model;

import java.time.LocalDateTime;
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
import java.util.Objects;


public class Event {
<<<<<<< HEAD
	private Integer id;
=======
	private Integer reviewIndex=0;
	private static Integer index = 0;
	private final Integer id;
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
	private String name;
	private String description;
	private LocalDateTime date;
	private String contactEmail;
	private String website;
	private Integer price;
	private String organizators;
<<<<<<< HEAD
	private String type;
	private String transport;
=======
	private List<Review> reviews= new ArrayList<Review>();
	private enum types {
		music,theater, art, cultural;
	};
	private enum transport {
		bus,train, bicycle, underground;
	};
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
	
	public Event(String name, Integer price , LocalDateTime date, String contactEmail, String organizators) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
		this.contactEmail = contactEmail;
		this.organizators = organizators;
	}
	
<<<<<<< HEAD
	
	public Event( String name, String description, Integer price , LocalDateTime date, String website, String contactEmail, String type, 
			String transport,String organizators) {
=======
	private Event(String name, String description, Integer price , LocalDateTime date, Coordinates location, String website, String email, types type, 
			transport transport,String organizators) {
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.date = date;
		this.website = website;
		this.contactEmail = contactEmail;
		this.type = type;
		this.transport= transport;
		this.organizators = organizators;
	}

<<<<<<< HEAD

	public static Event createEvent(String name, Integer price, LocalDateTime date, String contactEmail, String organizators) {
		return new Event(name, price, date, contactEmail,organizators);
	}

	public static Event createEvent( String name, String description, Integer price, LocalDateTime date, String website,  String contactEmail, String type, String transport, String organizators) {
		return new Event(name, description,price,date,website,contactEmail,type,transport,organizators);
	}



	public Integer getId() {
		return id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTransport() {
		return transport;
	}


	public void setTransport(String transport) {
		this.transport = transport;
	}


	public void setId(Integer id) {
		this.id = id;
	}


=======
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		Event.index = index;
	}
	
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

<<<<<<< HEAD

	public String getDate() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return date.format(f);
=======
	public LocalDateTime getDate() {
		return date;
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

<<<<<<< HEAD

	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
=======
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getOrganizators() {
		return organizators;
	}

	public void setOrganizators(String organizators) {
		this.organizators = organizators;
	}

<<<<<<< HEAD

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date
				+ ", contactEmail=" + contactEmail + ", website=" + website + ", price=" + price + ", organizators="
				+ organizators + "]";
	}


=======
	public Coordinates getLocation() {
		return location;
	}
	
	public void addReview(Review review) {
		review.setId(reviewIndex);
		this.reviews.add(reviewIndex, review);
		reviewIndex++;
	}
	
	public Integer getNumReviews() {
		return this.reviews.size();
	}
	
	public Double getRating() {
		return this.reviews.stream().mapToDouble(r->r.getRating()).sum()/getNumReviews();
	}
	
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
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

<<<<<<< HEAD

	


	
	
	

=======
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", email="
				+ email + ", website=" + website + ", location=" + location + ", price=" + price + ", organizators="
				+ organizators + "]";
	}
>>>>>>> 6753d1ca96351d63fde0704f75faf442da5e6127
}
