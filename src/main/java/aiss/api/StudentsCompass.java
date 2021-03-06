package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resources.EventResource;
import aiss.api.resources.PlaceResource;
import aiss.exceptions.BadEntityRequestExceptionMapper;
import aiss.exceptions.EntityNotFoundExceptionMapper;

public class StudentsCompass extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public StudentsCompass() {
		singletons.add(PlaceResource.getInstance());
		singletons.add(EventResource.getInstance());
		singletons.add(new EntityNotFoundExceptionMapper());
		singletons.add(new BadEntityRequestExceptionMapper());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
