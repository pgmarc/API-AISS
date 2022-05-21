package aiss.util;

import java.util.ArrayList;
import java.util.List;

import aiss.model.Coordinates;
import aiss.model.Place;
import aiss.model.PlaceCategory;

public class PlaceData {

	public static List<Place> getPlacesInfo() {
		
		List<Place> places = new ArrayList<Place>();
		
		// ETSII - Virgen del Rocio 960,60 metros
		// ETSII - CRAI  182,68 metros
		// ETSII - Benito Villamarin 495 metros
		// ETSII - Cerveceria Huracan 278,30 metros
		// ETSII - Estanialo 802,04 metros
		// ETSII - CASH FRESH 877,30
		
		Place place1 = new Place();
		place1.setName("Escuela Técnica Superior de Ingeniería Informática");
		place1.setEmail("info-eii@listas.us.es");
		place1.setAddress("Av. Reina Mercedes s/n");
		place1.setWebsite("https://informatica.us.es");
		place1.setCategory(PlaceCategory.UNIVERSITY);
		place1.setLocation(Coordinates.of(37.35822545590968, -5.986939772283946));

		Place place2 = new Place();
		place2.setName("CRAI Antonio de Ulloa");
		place2.setEmail("biblioteca@us.es");
		place2.setAddress("Av. Reina Mercedes s/n");
		place2.setWebsite("https://bib.us.es/ulloa");
		place2.setCategory(PlaceCategory.UNIVERSITY);
		place2.setLocation(Coordinates.of(37.359955803184626, -5.986567300045642));
		
		Place place3 = new Place();
		place3.setName("Cervecería Huracán");
		place3.setAddress("Av. Padre García Tejero, 13, 41012 Sevilla");
		place3.setCategory(PlaceCategory.BAR);
		place3.setLocation(Coordinates.of(37.356144231776064, -5.98515939773195));
		
		Place place4 = new Place();
		place4.setName("Estadio Benito Villamarín");
		place4.setEmail("info@realbetisbalompie.es");
		place4.setAddress("Av de Heliópolis, s/n, 41012 Sevilla");
		place4.setCategory(PlaceCategory.STADIUM);
		place4.setWebsite("https://www.realbetisbalompie.es");
		place4.setLocation(Coordinates.of(37.35659047062606, -5.981751164193446));
		
		Place place5 = new Place();
		place5.setName("Jazzy");
		place5.setEmail("rnairne4@un.org");
		place5.setAddress("09 Montana Place");
		place5.setWebsite("https://reference.com");
		place5.setCategory(PlaceCategory.UNDEFINED);
		place5.setLocation(Coordinates.of(-42.7700601,-65.0306302));
		
		Place place6 = new Place();
		place6.setName("Residencia universitaria Estanislao del Campo");
		place6.setEmail("estanislao@micampusliving.com");
		place6.setAddress("Ctra. Su Eminencia, 2A, 41013 Sevilla");
		place6.setWebsite("https://micampusresidencias.com/micampus-estanislao");
		place6.setCategory(PlaceCategory.ACCOMODATION);
		place6.setLocation(Coordinates.of(37.35649060120476, -5.9781261663374625));
		
		Place place7 = new Place();
		place7.setName("Hospital Universitario Virgen del Rocío");
		place7.setAddress("Av. Manuel Siurot, S/n, 41013 Sevilla");
		place7.setWebsite("http://hospitaluvrocio.es");
		place7.setCategory(PlaceCategory.HOSPITAL);
		place7.setLocation(Coordinates.of(37.36297551583045, -5.9777678016437354));
		
		Place place8 = new Place();
		place8.setName("CASH FRESH");
		place8.setEmail("atcliente@supermercadosmas.com");
		place8.setAddress("C. Gustavo Gallardo, 6, 41013 Sevilla");
		place8.setWebsite("http://www.supermercadosmas.com");
		place8.setCategory(PlaceCategory.SUPERMARKET);
		place8.setLocation(Coordinates.of(37.365602897591415, -5.9832610929518815));
		
		Place place9 = new Place();
		place9.setName("La Gorda de las Delicias");
		place9.setAddress("Paseo de las Delicias 3, Local ACC, 41013 Sevilla");
		place9.setCategory(PlaceCategory.BAR);
		place9.setLocation(Coordinates.of(37.38195001758691, -5.9954137722308705));
		
		Place place10 = new Place();
		place10.setName("Fibes Palacio de Congresos");
		place10.setEmail("info@sevillacityoffice.es");
		place10.setAddress("Av. Alcalde Luis Uruñuela, 41000 Sevilla");
		place10.setWebsite("https://home.fibes.es");
		place10.setCategory(PlaceCategory.CONGRESS);
		place10.setLocation(Coordinates.of(37.40353893779948, -5.934762897272381));
		
		Place place11 = new Place();
		place11.setName("Estación de Autobuses Plaza de Armas");
		place11.setEmail("info@autobusesplazadearmas.es");
		place11.setAddress("Puente Cristo de la Expiración, 2, 41001 Sevilla");
		place11.setWebsite("http://www.autobusesplazadearmas.es");
		place11.setCategory(PlaceCategory.BUS_STATION);
		place11.setLocation(Coordinates.of(37.39101613670735, -6.003420901094104));
		
		places.add(place1);
		places.add(place2);
		places.add(place3);
		places.add(place4);
		places.add(place5);
		places.add(place6);
		places.add(place7);
		places.add(place8);
		places.add(place9);
		places.add(place10);
		places.add(place11);

		return places;
	}
}
