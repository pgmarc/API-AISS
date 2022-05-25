package aiss.util;

import java.io.IOException;
import java.time.LocalDateTime;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import aiss.exceptions.BadEntityRequestException;
import aiss.util.validation.DateValidation;

public class DateDeserialization extends StdDeserializer<LocalDateTime> {
	
	public DateDeserialization() {
		this(null);
	}

	protected DateDeserialization(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			JsonNode node = jp.getCodec().readTree(jp);
			String formatedDate = node.getTextValue();
			if (!DateValidation.validDateTime(formatedDate)) 
				throw new JsonMappingException("Unprocessable DateTime."
						+ " DateTime should be formated: yyyy-MM-dd HH:mm."
						+ " Hours should be formated: 00:00 - 23:59");
			return DateValidation.parseLocalDateTime(formatedDate);
		} catch (Exception e) {
			throw new BadEntityRequestException(e.getMessage());
		}	    
	}
}
