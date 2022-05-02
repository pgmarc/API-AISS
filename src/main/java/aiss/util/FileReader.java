package aiss.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aiss.model.Coordinates;
import aiss.model.Place;

public class FileReader {
	
	public static void main(String[] args) {
		String path = "files/places.csv";
		
		
	
	}
	
	public static List<Place> readPlacesFromCSV(String filepath) {
		List<Place> places = new ArrayList<Place>(); 
		try {
			places = Files.
					lines(Paths.get(filepath), Charset.defaultCharset())
					.skip(1).limit(10).map(FileReader::createPlace)
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
		Integer rating = Integer.valueOf(fields[3]); 
		String website = fields[4]; 
		Double latitude = Double.valueOf(fields[5]); 
		Double longitude = Double.valueOf(fields[6]);
		
		Coordinates coordinates = Coordinates.of(latitude, longitude);
		return Place.create(name, email, address, rating, website, coordinates);
	}
	

}
