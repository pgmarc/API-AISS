package aiss.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import aiss.model.Coordinates;
import aiss.model.Place;

public class FileReader {

	public static Map<Integer, Place> readPlacesFromCSV(String filepath) {
		Map<Integer,Place> places = new HashMap<Integer,Place>(); 
		try {
			places = Files.lines(Paths.get(filepath), Charset.defaultCharset())
					.skip(1).limit(50).map(FileReader::createPlace)
					.collect(Collectors.toMap(Place::getId, Function.identity()));
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
	
	public static void main(String[] args) {
		Map<Integer, Place> p = readPlacesFromCSV("files/places.csv");
		System.out.println(p.values());
	}
}
