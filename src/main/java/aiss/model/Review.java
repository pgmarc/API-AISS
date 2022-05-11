package aiss.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Review {
	
	private Integer id;
	private String username;
	private String description;
	private Integer rating;
	private LocalDateTime date;
	
	public Review(String username, String description, Integer rating) {
		super();
		this.username = username;
		this.description = description;
		this.rating = rating;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(date, other.date) && Objects.equals(username, other.username);
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", username=" + username + ", description=" + description + ", rating=" + rating
				+ ", date=" + date;
	}
}
