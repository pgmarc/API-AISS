package aiss.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import aiss.model.ErrorMessage;

@Provider
public class BadEntityRequestExceptionMapper implements ExceptionMapper<BadEntityRequestException> {

	@Override
	public Response toResponse(BadEntityRequestException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorMessage(exception.getMessage());
		errorMessage.setCode(400);
		errorMessage
		.setDocumentation("https://student-compass-350106.ew.r.appspot.com/docs/index.html");
		
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}
}
