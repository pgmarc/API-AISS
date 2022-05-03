package aiss.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
	private static Integer index = 0;
	private final Integer id;
	private String name;
	private String description;
	private LocalDateTime date;
	private String email;
	private String website;
	private final Coordinates location;
	private Integer price;
	private String organizators;
	private enum types {
		music,theater, art, cultural;
	};
	private enum transport {
		bus,train, bicycle, underground;
	};
	
	private Event(String name, Integer price , LocalDateTime date, Coordinates location, String email, String organizators) {
		super();
		this.id = index;
		this.name = name;
		this.price = price;
		this.date = date;
		this.location = location;
		this.email = email;
		this.organizators = organizators;
		index++;
	}
	
	
	private Event(String name, String description, Integer price , LocalDateTime date, Coordinates location, String website, String email, types type, 
			transport transport,String organizators) {
		super();
		this.id = index;
		this.name = name;
		this.description = description;
		this.price = price;
		this.date = date;
		this.location = location;
		this.website = website;
		this.email = email;
		this.organizators = organizators;
		index++;
	}


	public Integer getIndex() {
		return index;
	}


	public void setIndex(Integer index) {
		Event.index = index;
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


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public Coordinates getLocation() {
		return location;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, description, email, id, location, name, organizators, price, website);
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
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(organizators, other.organizators) && Objects.equals(price, other.price)
				&& Objects.equals(website, other.website);
	}


	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", email="
				+ email + ", website=" + website + ", location=" + location + ", price=" + price + ", organizators="
				+ organizators + "]";
	}
	
	
	
	
	
	

}
