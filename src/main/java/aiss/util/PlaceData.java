package aiss.util;

import java.util.ArrayList;
import java.util.List;

import aiss.model.Accomodation;
import aiss.model.Accomodation.AccomodationType;
import aiss.model.AccomodationPayment;
import aiss.model.AccomodationPayment.MealService;
import aiss.model.AccomodationPayment.PaymentPeriod;
import aiss.model.AccomodationPayment.RoomType;
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
		
		Place place12 = new Place();
		place12.setName("Residencia universitaria micampus Armendáriz");
		place12.setEmail("armendariz@micampusliving.com");
		place12.setAddress("Ctra. Su Eminencia 15, 41013 Sevilla");
		place12.setWebsite("https://micampusresidencias.com/sevilla-armendariz/");
		place12.setCategory(PlaceCategory.ACCOMODATION);
		place12.setLocation(Coordinates.of(37.1237613670735, -6.12381094104));
		
		Place place13 = new Place();
		place13.setName("Piso de totalmente incuestionable calidad");
		place13.setEmail("elmejorpisitodelmundo@localshadybusiness.es");
		place13.setAddress("El callejón ese oscuro y sospechoso, 2, 41001 Sevilla");
		place13.setWebsite("http://www.localshadybusiness.es");
		place13.setCategory(PlaceCategory.ACCOMODATION);
		place13.setLocation(Coordinates.of(37.1237613670735, -6.12381094104));
		
		Place place14 = new Place();
		place14.setName("Residencia test 1");
		place14.setEmail("testing@gmail.com");
		place14.setAddress("Calle test, 2, 41001 Sevilla");
		place14.setWebsite("http://www.testing.es");
		place14.setCategory(PlaceCategory.ACCOMODATION);
		place14.setLocation(Coordinates.of(12.34567, -12.34567));
		
		Place place15 = new Place();
		place15.setName("Residencia test 2");
		place15.setEmail("testing2@gmail.com");
		place15.setAddress("Calle test 2, 2, 41001 Sevilla");
		place15.setWebsite("http://www.testing2.es");
		place15.setCategory(PlaceCategory.ACCOMODATION);
		place15.setLocation(Coordinates.of(21.34567, -21.34567));
		
		
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
		places.add(place12);
		places.add(place13);
		places.add(place14);

		Accomodation acc1 = new Accomodation(
				500, 
				List.of(
						new AccomodationPayment(
								"Individual con servicios extra",
								1500.1,
								PaymentPeriod.YEARLY,
								MealService.ALL_MEALS,
								RoomType.INDIVIDUAL),
						new AccomodationPayment(
								"Doble con servicios extra",
								1050.1,
								PaymentPeriod.YEARLY,
								MealService.ALL_MEALS,
								RoomType.INDIVIDUAL),
						new AccomodationPayment(
								"Individual con limpieza semanal",
								1200.1,
								PaymentPeriod.YEARLY,
								MealService.TWO_MEALS,
								RoomType.INDIVIDUAL),
						new AccomodationPayment(
								"Doble con limpieza semanal",
								900.1,
								PaymentPeriod.YEARLY,
								MealService.TWO_MEALS,
								RoomType.DOUBLE)
						),
				AccomodationType.RESIDENCE);
		place14.setAccomodation(acc1);
		
		Accomodation acc2 = new Accomodation(
				600, 
				List.of(
						new AccomodationPayment(
								"Dos habitaciones y un cuarto de baño, cocina pequeña",
								500.,
								PaymentPeriod.MONTHLY,
								MealService.SELF_CATERING,
								RoomType.OTHER)
						),
				AccomodationType.RESIDENCE);
		place15.setAccomodation(acc2);
		
		Accomodation acc3 = new Accomodation(
				800, 
				List.of(
						new AccomodationPayment(
								"Pensión completa, limpieza de habitación semanal, cambio"
								+ " de sábanas y toallas semanal, salón, cocina, baño completo, mesa de estudio,"
								+ " armario, climatización,"
								+ " WIFI y todos los beneficios de micampus Club.",
								628.,
								PaymentPeriod.MONTHLY,
								MealService.ALL_MEALS,
								RoomType.DOUBLE),
						new AccomodationPayment(
								"Pensión completa, limpieza de habitación semanal, cambio de sábanas y toallas semanal,"
								+ " salón grande, cocina, baño completo, mesa de estudio, armario, WIFI, "
								+ "climatización y todos los beneficios de micampus Club.",
								759.,
								PaymentPeriod.MONTHLY,
								MealService.ALL_MEALS,
								RoomType.INDIVIDUAL),
						new AccomodationPayment(
								"Limpieza de habitación semanal, "
								+ "cambio de sábanas y toallas semanal, 2 amplias "
								+ "habitaciones, salón grande, baño  y cocina completos, mesa "
								+ "de estudio, armario, climatización, WIFI y todos los "
								+ "beneficios de micampus Club.",
								807.,
								PaymentPeriod.MONTHLY,
								MealService.SELF_CATERING,
								RoomType.DOUBLE),
						new AccomodationPayment(
								"Limpieza de habitación semanal, "
								+ "cambio de sábanas y toallas semanal, amplia "
								+ "habitación, salón grande, baño y cocina completos, mesa "
								+ "de estudio, armario, climatización, WIFI y todos los "
								+ "beneficios de micampus Club.",
								1011.1,
								PaymentPeriod.MONTHLY,
								MealService.SELF_CATERING,
								RoomType.INDIVIDUAL)
						),
				AccomodationType.RESIDENCE);
		place12.setAccomodation(acc3);
		
		Accomodation ac4 = new Accomodation(
				1200,
				List.of(
						new AccomodationPayment(
								"3 literas dobles y un baño compartido"
								+ " para nada en malas condiciones",
								199.99,
								PaymentPeriod.MONTHLY,
								MealService.TWO_MEALS,
								RoomType.OTHER),
						new AccomodationPayment(
								"4 literas dobles en una habitación muy muy espaciosa y con"
								+ " cocina y baños de la máxima calidad",
								149.99,
								PaymentPeriod.MONTHLY,
								MealService.SELF_CATERING,
								RoomType.OTHER)
						),
				AccomodationType.FLAT);
		place13.setAccomodation(ac4);
		
		Accomodation ac5 = new Accomodation(
				850,
				List.of(
						new AccomodationPayment(
								"Media pensión (con opción de pensión completa), "
								+ "limpieza semanal incluida, baño propio, cocina "
								+ "equipada, escritorio, agua, climatización, WIFI, "
								+ "electricidad, televisión y todos los beneficios de "
								+ "micampus Club",
								615.1,
								PaymentPeriod.MONTHLY,
								MealService.TWO_MEALS,
								RoomType.DOUBLE
								),
						new AccomodationPayment(
								"Media pensión (con opción de pensión completa), "
								+ "limpieza semanal incluida, baño propio, cocina "
								+ "equipada compartida, escritorio, agua, climatización, WIFI, "
								+ "electricidad, televisión y todos los beneficios de "
								+ "micampus Club",
								699.,
								PaymentPeriod.MONTHLY,
								MealService.TWO_MEALS,
								RoomType.INDIVIDUAL)
						),
				AccomodationType.RESIDENCE
				);
		place6.setAccomodation(ac5);
		
		return places;
	}
}
