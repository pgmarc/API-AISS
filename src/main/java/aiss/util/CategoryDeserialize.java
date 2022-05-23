package aiss.util;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import aiss.model.PlaceCategory;

public class CategoryDeserialize extends StdDeserializer<PlaceCategory>{
	
	public CategoryDeserialize() {
		this(null);
	}

	protected CategoryDeserialize(Class<?> vc) {
		super(vc);
	}

	@Override
	public PlaceCategory deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
	    String category = node.getTextValue().toUpperCase();
	    
	    return PlacesUtil.getValidPlaceCategory(category);
	}
}
