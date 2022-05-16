package aiss.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aiss.model.Coordinates;
import aiss.model.Place;

public class FileReader {

	public static List<Place> readPlacesFromCSV(String filepath) {
		List<Place> places = new ArrayList<Place>();
		InputStream i= FileReader.class.getResourceAsStream("places.csv");
			try {
				return Files.lines(Paths.get(filepath), Charset.defaultCharset())
						.skip(1).limit(50).map(FileReader::createPlace)
						.collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		return places;
	}
	
	private static Place createPlace(String line) {
		String[] fields = line.split(",");
		String name = fields[0];
		String email = fields[1]; 
		String address = fields[2]; 
		String website = fields[4]; 
		Double latitude = Double.valueOf(fields[5]); 
		Double longitude = Double.valueOf(fields[6]);
		
		Coordinates coordinates = Coordinates.of(latitude, longitude);
		return Place.create(name, email, address, website, coordinates);
	}
}
