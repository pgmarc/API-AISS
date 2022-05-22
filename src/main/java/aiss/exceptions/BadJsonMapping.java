package aiss.exceptions;

import org.codehaus.jackson.map.JsonMappingException;

public class BadJsonMapping extends JsonMappingException {

	private static final long serialVersionUID = 1L;

	public BadJsonMapping(String msg) {
		super(msg);
	}

}
