package aiss.model;

import java.util.Objects;

public class Place {
	
	private static Integer index = 0;
	private final Integer id;
	private String name;
	private String email;
	private String address;
	private Integer rating;
	private String website;
	private final Coordinates location;
	
	private Place(String name, String address, Coordinates location) {
		super();
		this.id = index;
		this.name = name;
		this.address = address;
		this.location = location;
		index++;
	}
	
	private Place(String name, String email, String address, Integer rating, String website, Coordinates location) {
		super();
		this.id = index;
		this.name = name;
		this.email = email;
		this.address = address;
		this.rating = rating;
		this.website = website;
		this.location = location;
		index++;
	}

	public static Place create(String name, String email, String address, Integer rating, String website, Coordinates location) {
		return new Place(name, email, address, rating, website, location);
	}

	public static Place create(String name, String address, Coordinates location) {
		return new Place(name, address, location);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getId() {
		return id;
	}

	public Coordinates getLocation() {
		return location;
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
