package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Place;
import aiss.model.repository.MapApplicationRepository;
import aiss.model.repository.PlaceRepository;

@Path("/places")
public class PlaceResource {
	
	// Singleton	
	private static PlaceResource placeResource = null;
	private PlaceRepository placeRepository;
	
	private PlaceResource() {
		this.placeRepository = MapApplicationRepository.getInstance();
	}
	
	public static PlaceResource getInstance() {
		if (placeResource == null) {
			placeResource = new PlaceResource();
		}
		return placeResource;
	}
	
	@GET
	@Produces("application/json")
	public Response getAll() {
		List<Place> places = new ArrayList<Place>(placeRepository.getAllPlaces());
		return Response.status(Status.OK).entity(places).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getPlace(@PathParam("id") Integer placeId) {
		Place place = placeRepository.getPlace(placeId);
		return Response.status(Status.OK).entity(place).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlace(@Context UriInfo uriInfo, Place place) {
		
		if (place.getAddress() == null || "".equals(place.getAddress()))
			throw new BadRequestException("The address of a place must not be null");
		
		if (place.getId() !=null)
			throw new BadRequestException("The place id must not been given as a parameter.");

		placeRepository.addPlace(place);

		// Builds the response. Returns the place the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder(). path(this.getClass(), "getPlace");
		URI uri = ub.build(place.getId());
		ResponseBuilder response = Response.created(uri);
		response.entity(place);			
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updatePlace(@PathParam("id") Integer placeId,Place place) {
		Place oldPlace = placeRepository.getPlace(placeId);
		if (oldPlace == null)
			throw new NotFoundException("The place with id="+ place.getId() +" was not found");			
		
		if (place.getAddress() != null)
			oldPlace.setAddress(place.getAddress());
		
		if (place.getEmail() != null)
			oldPlace.setEmail(place.getEmail());
		
		if (place.getName() != null)
			oldPlace.setName(place.getName());
		
		if (place.getRating() != null)
			oldPlace.setRating(place.getRating());
		
		if (place.getWebsite() != null)
			oldPlace.setWebsite(place.getWebsite());
		
		placeRepository.updatePlace(oldPlace);
		
		return Response.status(Status.NO_CONTENT).entity(oldPlace).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletePlace(@PathParam("id") Integer placeId) {
		Place placeToBeRemoved = placeRepository.getPlace(placeId);
		if (placeToBeRemoved == null)
			throw new NotFoundException("The place with id=" + placeId + " was not found");
		else
			placeRepository.deletePlace(placeId);
		
		return Response.noContent().build();
	}
	
	public static void main(String[] args) {
		System.out.println(getInstance().placeRepository.getAllPlaces());
	}
}
