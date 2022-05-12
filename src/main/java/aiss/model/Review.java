package aiss.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({"id","username","description","date","rating"})
public class Review {
	

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("rating")
	private Double rating;

	private LocalDateTime date;
	
	@JsonCreator
	public Review(@JsonProperty("username") String username,
			@JsonProperty("description") String description,@JsonProperty("rating") Double rating) {
		super();
		this.username = username;
		this.description = description;
		this.rating = rating;
	}
	
	public Review() {
		super();
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
	
	public Double getRating() {
		return rating;
	}
	
	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	@JsonProperty("date")
	public String getDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return date.format(format);
	}
	
	@JsonIgnore
	public LocalDateTime getCompleteDate() {
		return this.date;
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
				+ ", date=" + getDate() + "]";
	}
}
