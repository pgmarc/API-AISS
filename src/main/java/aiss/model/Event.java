package aiss.model;

import java.time.LocalDateTime; 

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class Event {
	
	private Integer reviewIndex=0;
	private Integer id;
	private String name;
	private String description;
	private LocalDateTime date;
	private String contactEmail;
	private String website;
	private Integer price;
	private String organizators;
	private Types type;
	private Transport transport;
	private List<Review> reviews= new ArrayList<Review>();
	private enum Types {
		MUSIC,THEATRE, ART, CULTURAL;
	};
	private enum Transport {
		BUS,TRAIN, BICYCLE, UNDERGROUND;
	};
	
	public Event(String name, Integer price , LocalDateTime date, String contactEmail, String organizators) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
		this.contactEmail = contactEmail;
		this.organizators = organizators;
	}
	
	public Event(String name, String description, Integer price , LocalDateTime date, 
			String website, String contactEmail, Types type, Transport transport,String organizators) {
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

	public static Event createEvent(String name, Integer price, LocalDateTime date, String contactEmail,
			String organizators) {
		return new Event(name, price, date, contactEmail,organizators);
	}

	public static Event createEvent( String name, String description, Integer price,
										LocalDateTime date, String website,  
										String contactEmail, Types type, Transport transport, 
										String organizators) {
		return new Event(name, description,price,date,website,contactEmail,type,transport,organizators);
	}

	public Integer getId() {
		return id;
	}


	public Types getType() {
		return type;
	}


	public void setType(Types type) {
		this.type = type;
	}


	public Transport getTransport() {
		return transport;
	}


	public void setTransport(Transport transport) {
		this.transport = transport;
	}


	public void setId(Integer id) {
		this.id = id;
	}

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

	public LocalDateTime getDate() {
		return date;
	}
	
	public String getDateFormatted() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return date.format(f);
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
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
