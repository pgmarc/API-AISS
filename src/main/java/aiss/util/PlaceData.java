package aiss.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aiss.model.Accommodation;
import aiss.model.Accommodation.accommodationType;
import aiss.model.AccommodationPayment;
import aiss.model.AccommodationPayment.MealService;
import aiss.model.AccommodationPayment.PaymentPeriod;
import aiss.model.AccommodationPayment.RoomType;
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
		place6.setCategory(PlaceCategory.ACCOMMODATION);
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
		place12.setCategory(PlaceCategory.ACCOMMODATION);
		place12.setLocation(Coordinates.of(37.1237613670735, -6.12381094104));
		
		Place place13 = new Place();
		place13.setName("Piso de totalmente incuestionable calidad");
		place13.setEmail("elmejorpisitodelmundo@localshadybusiness.es");
		place13.setAddress("El callejón ese oscuro y sospechoso, 2, 41001 Sevilla");
		place13.setWebsite("http://www.localshadybusiness.es");
		place13.setCategory(PlaceCategory.ACCOMMODATION);
		place13.setLocation(Coordinates.of(37.1237613670735, -6.12381094104));
		
		Place place14 = new Place();
		place14.setName("Residencia test 1");
		place14.setEmail("testing@gmail.com");
		place14.setAddress("Calle test, 2, 41001 Sevilla");
		place14.setWebsite("http://www.testing.es");
		place14.setCategory(PlaceCategory.ACCOMMODATION);
		place14.setLocation(Coordinates.of(12.34567, -12.34567));
		
		Place place15 = new Place();
		place15.setName("Residencia test 2");
		place15.setEmail("testing2@gmail.com");
		place15.setAddress("Calle test 2, 2, 41001 Sevilla");
		place15.setWebsite("http://www.testing2.es");
		place15.setCategory(PlaceCategory.ACCOMMODATION);
		place15.setLocation(Coordinates.of(21.34567, -21.34567));
		
		Place place16 = new Place();
		place16.setName("Aeropuerto de Sevilla");
		place16.setEmail("svqcas@aena.es");
		place16.setAddress("Carretera N-IV Madrid-Cádiz, kilómetro 532");
		place16.setWebsite("https://www.aena.es");
		place16.setCategory(PlaceCategory.AIRPORT);
		place16.setLocation(Coordinates.of(37.418056, -5.898889));
		
		Place place17 = new Place();
		place17.setName("Supermercado el Jamón");
		place17.setEmail("hola@supermercadoseljamon.com");
		place17.setAddress("C. Bami, 21");
		place17.setWebsite("https://www.supermercadoseljamon.com");
		place17.setCategory(PlaceCategory.SUPERMARKET);
		place17.setLocation(Coordinates.of(37.418056, -5.898889));
		
		Place place18 = new Place();
		place18.setName("Farmacia los Bermejales");
		place18.setEmail("josearafael@hotmail.com");
		place18.setAddress("Avenida Paseo de Europa , 2");
		place18.setWebsite("https://www.sensafarma.es/farmacia/farmacia-los-bermejales--003519");
		place18.setCategory(PlaceCategory.PHARMACY);
		place18.setLocation(Coordinates.of(37.347413443216624, -5.977527613329705));
		
		Place place19 = new Place();
		place19.setName("Cajero Automático Caja Rural");
		place19.setEmail("cajarural@hotmail.com");
		place19.setAddress("AV. REINO UNIDO , esq, C. Dresde, 1");
		place19.setWebsite("https://www.grupocajarural.es/en");
		place19.setCategory(PlaceCategory.ATM);
		place19.setLocation(Coordinates.of(37.347805781771854, -5.97701262919528));
		
		Place place20 = new Place();
		place20.setName("Silken Al-Andalus Palace");
		place20.setEmail("hotelsilken@silken.es");
		place20.setAddress("Avenida de la Palmera, S/N, esq. calle Paraná.");
		place20.setWebsite("https://www.hoteles-silken.com/en/hotel-al-andalus-seville");
		place20.setCategory(PlaceCategory.BUSINESS);
		place20.setLocation(Coordinates.of(37.354232, -5.980253));
		
		Place place21 = new Place();
		place21.setName("Casa Félix");
		place21.setEmail("hola@casafelix.com");
		place21.setAddress("Av. de la Reina Mercedes, 51");
		place21.setWebsite("https://www.casafelix.com");
		place21.setCategory(PlaceCategory.RESTAURANT);
		place21.setLocation(Coordinates.of(37.3582028488584, -5.986044012031853));
		
		Place place22 = new Place();
		place22.setName("Iglesia De La Virgen Del Carmen Los Bermejales");
		place22.setEmail("parroquiamar@gmail.com");
		place22.setAddress("Calle de la Corbeta, s/n; ");
		place22.setWebsite("http://www.parroquiamar.com");
		place22.setCategory(PlaceCategory.CHURCH);
		place22.setLocation(Coordinates.of(37.35027112578121, -5.978104559623624));
		
		Place place23 = new Place();
		place23.setName("Cine Yelmo Premium Lagoh");
		place23.setEmail("cineslagoh@hotmail.com");
		place23.setAddress(" Centro Comercial Lagoh, Av. de Palmas Altas, S/N,");
		place23.setWebsite("https://yelmocines.es/cartelera/sevilla/-premium-lagoh");
		place23.setCategory(PlaceCategory.CINEMA);
		place23.setLocation(Coordinates.of(37.34125068226267, -5.98645935566553));
		
		Place place24 = new Place();
		place24.setName("Parque de María Luisa");
		place24.setEmail("parquemarialuisas@sevilla.es");
		place24.setAddress("P.º de las Delicias, s/n, 41013 Sevilla");
		place24.setWebsite("https://www.sevilla.org");
		place24.setCategory(PlaceCategory.PARK);
		place24.setLocation(Coordinates.of(37.374748653723486, -5.988248783469929));
		
		Place place25 = new Place();
		place25.setName("KOKO");
		place25.setEmail("hola@koko.com");
		place25.setAddress("Pl. de la Encarnación, 38");
		place25.setWebsite("https://www.koko.com");
		place25.setCategory(PlaceCategory.NIGHT_CLUB);
		place25.setLocation(Coordinates.of(37.39347634268463, -5.991833855581043));
		
		Place place26 = new Place();
		place26.setName("Museo Bellas Artes Sevilla");
		place26.setEmail("museobellasartessevilla.ccul@juntadeandalucia.es");
		place26.setAddress("Pl. del Museo, 9");
		place26.setWebsite("https://www.museosdeandalucia.es/web/museodebellasartesdesevilla");
		place26.setCategory(PlaceCategory.MUSEUM);
		place26.setLocation(Coordinates.of(37.39347634268463, -5.991833855581043));
		
		Place place27 = new Place();
		place27.setName("Freedom Fitness");
		place27.setEmail("info@freedomfitness.es");
		place27.setAddress("Calle Luxemburgo 5");
		place27.setWebsite("https://freedomfitness.es");
		place27.setCategory(PlaceCategory.GYM);
		place27.setLocation(Coordinates.of(37.3460189387598, -5.981073975326615));
		
		Place place28 = new Place();
		place28.setName("Sánchez-Pizjuán");
		place28.setEmail("fundacion@sevillafc.es");
		place28.setAddress("C. Sevilla Fútbol Club, 41005 Sevilla");
		place28.setWebsite("https://sevillafc.es/el-club/ramon-sanchez-pizjuan");
		place28.setCategory(PlaceCategory.STADIUM);
		place28.setLocation(Coordinates.of(37.38397029952987, -5.971065147154966));
		
		Place place29 = new Place();
		place29.setName("Fibes - Palacio de Congresos y Exposiciones Sevilla");
		place29.setEmail("info@sevillacityoffice.es");
		place29.setAddress("Av. Alcalde Luis Uruñuela, 1, 41020 Sevilla");
		place29.setWebsite("https://home.fibes.es/");
		place29.setCategory(PlaceCategory.CONGRESS);
		place29.setLocation(Coordinates.of(37.40404096638718, -5.934864515736523));
		
		Place place30 = new Place();
		place30.setName("Hospital Universitario Virgen del Rocío");
		place30.setEmail("atencionciudadana.hg.huv.sspa@juntadeandalucia.es.");
		place30.setAddress("Av. Manuel Siurot, S/n, 41013 Sevilla");
		place30.setWebsite("https://www.hospitaluvrocio.es/");
		place30.setCategory(PlaceCategory.HOSPITAL);
		place30.setLocation(Coordinates.of(37.363610013961534, -5.9775810386218575));
		
		Place place31 = new Place();
		place31.setName("Jefatura de Policía");
		place31.setEmail("distrito9.pl@sevilla.org");
		place31.setAddress("C. Clemente Hidalgo, 4, Local, 41005 Sevilla");
		place31.setWebsite("https://www.sevilla.org/servicios/policia-local");
		place31.setCategory(PlaceCategory.POLICE);
		place31.setLocation(Coordinates.of(37.38493806149119, -5.959976375042552));
		
		Place place32 = new Place();
		place32.setName("Catedral de Sevilla");
		place32.setEmail("info@catedraldesevilla.es");
		place32.setAddress("Av. de la Constitución, s/n, 41004 Sevilla");
		place32.setWebsite("https://www.catedraldesevilla.es/");
		place32.setCategory(PlaceCategory.TOURIST_ATTRACTION);
		place32.setLocation(Coordinates.of(37.38310380725238, -5.990379038596782));
		
		Place place33 = new Place();
		place33.setName("Galp - Estación de Servicio 24H");
		place33.setEmail("atencionalcliente@galp.com");
		place33.setAddress("C. Luis Montoto, 138, 41005 Sevilla");
		place33.setWebsite("https://galp.com/es/mapa");
		place33.setCategory(PlaceCategory.GAS_STATION);
		place33.setLocation(Coordinates.of(37.38712039307478, -5.968592898117942));
		
		Place place34 = new Place();
		place34.setName("Estacion Cercanias Virgen del Rocio");
		place34.setEmail(null);
		place34.setAddress("Sevilla-Virgen del Rocio, 41013 Sevilla");
		place34.setWebsite("https://www.renfe.com/es/es/cercanias/cercanias-sevilla");
		place34.setCategory(PlaceCategory.TRAIN_STATION);
		place34.setLocation(Coordinates.of(37.36290042841052, -5.976304607411748));
		
		Place place35 = new Place();
		place35.setName("Sevilla - Estación Prado de San Sebastián");
		place35.setEmail(null);
		place35.setAddress("Sevilla - Estación Prado de San Sebastián, 41003 Sevilla");
		place35.setWebsite("https://www.tussam.es/es");
		place35.setCategory(PlaceCategory.BUS_STATION);
		place35.setLocation(Coordinates.of(37.38289970491382, -5.986602043311963));
		
		Place place36 = new Place();
		place36.setName("Parking Paseo de Colón");
		place36.setEmail("info@parclick.com");
		place36.setAddress(" P.º de Cristóbal Colón, 10, 41001 Sevilla");
		place36.setWebsite("https://parclick.es/parking-sevilla/paseo-de-colon");
		place36.setCategory(PlaceCategory.PARKING);
		place36.setLocation(Coordinates.of(37.38721632601805, -6.003493612274087));
		
		Place place37 = new Place();
		place37.setName("Taxi Sevilla Aeropuerto");
		place37.setEmail("taxiaeropuertosevilla@gmail.com");
		place37.setAddress("C. Antonio Susillo, 6, 41002 Sevilla");
		place37.setWebsite("https://www.taxissevilla.com/");
		place37.setCategory(PlaceCategory.TAXI_STAND);
		place37.setLocation(Coordinates.of(37.40051754627751, -5.985377026393285));
		
		Place place38 = new Place();
		place38.setName("San Bernardo");
		place38.setEmail("atencion.cliente@metrodesevilla.es");
		place38.setAddress("41018 Sevilla");
		place38.setWebsite("https://www.metro-sevilla.es/");
		place38.setCategory(PlaceCategory.SUBWAY_STATION);
		place38.setLocation(Coordinates.of(37.37921478508821, -5.982531615934994));
		
		Place place39 = new Place();
		place39.setName("Piscina Tiro De Línea");
		place39.setEmail("informacion@imd.sevilla.org");
		place39.setAddress("C. Lora del Río, s/n, 41001 Sevilla");
		place39.setWebsite("https://imd.sevilla.org/");
		place39.setCategory(PlaceCategory.UNDEFINED);
		place39.setLocation(Coordinates.of(37.36858432026401, -5.974291870221682));
		
		Place place40 = new Place();
		place40.setName("Basílica Santuario de María Auxiliadora");
		place40.setEmail("basilica.trinidad@salesianos.edu");
		place40.setAddress("C. María Auxiliadora, 41008 Sevilla");
		place40.setWebsite("https://trinidad.salesianos.edu/basilica-maria-auxiliadora-2/");
		place40.setCategory(PlaceCategory.CHURCH);
		place40.setLocation(Coordinates.of(37.404759099886036, -5.981090022414422));
		
		Place place41 = new Place();
		place41.setName("Estacion Metro Puerta Jerex");
		place41.setEmail("atencion.cliente@metrodesevilla.es");
		place41.setAddress("Av Puerta Cristina s/n");
		place41.setWebsite("https://www.metro-sevilla.es");
		place41.setCategory(PlaceCategory.SUBWAY_STATION);
		place41.setLocation(Coordinates.of(37.38198127961003, -5.994129898272375));
		
		
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
		places.add(place15);
		places.add(place16);
		places.add(place17);
		places.add(place18);
		places.add(place19);
		places.add(place20);
		places.add(place21);
		places.add(place22);
		places.add(place23);
		places.add(place24);
		places.add(place25);
		places.add(place26);
		places.add(place27);
		places.add(place28);
		places.add(place29);
		places.add(place30);
		places.add(place31);
		places.add(place32);
		places.add(place33);
		places.add(place34);
		places.add(place35);
		places.add(place36);
		places.add(place37);
		places.add(place38);
		places.add(place39);
		places.add(place41);
		
		
		Accommodation acc1 = new Accommodation(
				500, 
				new HashMap<>(),
				accommodationType.RESIDENCE);
				AccommodationPayment pay1 = new AccommodationPayment(
								"Individual con servicios extra",
								1500.1,
								PaymentPeriod.YEARLY,
								MealService.ALL_MEALS,
								RoomType.INDIVIDUAL);
				AccommodationPayment pay2 = new AccommodationPayment(
								"Doble con servicios extra",
								1050.1,
								PaymentPeriod.YEARLY,
								MealService.ALL_MEALS,
								RoomType.INDIVIDUAL);
				AccommodationPayment pay3 = new AccommodationPayment(
								"Individual con limpieza semanal",
								1200.1,
								PaymentPeriod.YEARLY,
								MealService.TWO_MEALS,
								RoomType.INDIVIDUAL);
				AccommodationPayment pay4 = new AccommodationPayment(
								"Doble con limpieza semanal",
								900.1,
								PaymentPeriod.YEARLY,
								MealService.TWO_MEALS,
								RoomType.DOUBLE);
		acc1.addPayment(pay1);
		acc1.addPayment(pay2);
		acc1.addPayment(pay3);
		acc1.addPayment(pay4);
		place14.setaccommodation(acc1);
		
		
		Accommodation acc2 = new Accommodation(
				600, 
				new HashMap<>(),
				accommodationType.RESIDENCE);
		AccommodationPayment pay5 = new AccommodationPayment(
								"Dos habitaciones y un cuarto de baño, cocina pequeña",
								500.,
								PaymentPeriod.MONTHLY,
								MealService.SELF_CATERING,
								RoomType.OTHER);
		acc2.addPayment(pay5);
		place15.setaccommodation(acc2);
		
		Accommodation acc3 = new Accommodation(
				800, 
				new HashMap<>(),
				accommodationType.RESIDENCE);
		AccommodationPayment pay6 = new AccommodationPayment(
						"Pensión completa, limpieza de habitación semanal, cambio"
						+ " de sábanas y toallas semanal, salón, cocina, baño completo, mesa de estudio,"
						+ " armario, climatización,"
						+ " WIFI y todos los beneficios de micampus Club.",
						628.,
						PaymentPeriod.MONTHLY,
						MealService.ALL_MEALS,
						RoomType.DOUBLE);
		AccommodationPayment pay7 = new AccommodationPayment(
						"Pensión completa, limpieza de habitación semanal, cambio de sábanas y toallas semanal,"
						+ " salón grande, cocina, baño completo, mesa de estudio, armario, WIFI, "
						+ "climatización y todos los beneficios de micampus Club.",
						759.,
						PaymentPeriod.MONTHLY,
						MealService.ALL_MEALS,
						RoomType.INDIVIDUAL);
		AccommodationPayment pay8 = new AccommodationPayment(
						"Limpieza de habitación semanal, "
						+ "cambio de sábanas y toallas semanal, 2 amplias "
						+ "habitaciones, salón grande, baño  y cocina completos, mesa "
						+ "de estudio, armario, climatización, WIFI y todos los "
						+ "beneficios de micampus Club.",
						807.,
						PaymentPeriod.MONTHLY,
						MealService.SELF_CATERING,
						RoomType.DOUBLE);
		AccommodationPayment pay9 = new AccommodationPayment(
						"Limpieza de habitación semanal, "
						+ "cambio de sábanas y toallas semanal, amplia "
						+ "habitación, salón grande, baño y cocina completos, mesa "
						+ "de estudio, armario, climatización, WIFI y todos los "
						+ "beneficios de micampus Club.",
						1011.1,
						PaymentPeriod.MONTHLY,
						MealService.SELF_CATERING,
						RoomType.INDIVIDUAL);

		acc3.addPayment(pay6);
		acc3.addPayment(pay7);
		acc3.addPayment(pay8);
		acc3.addPayment(pay9);
		place12.setaccommodation(acc3);
		
		Accommodation ac4 = new Accommodation(
				1200,
				new HashMap<>(),
				accommodationType.FLAT);
		AccommodationPayment pay10 = new AccommodationPayment(
						"3 literas dobles y un baño compartido"
						+ " para nada en malas condiciones",
						199.99,
						PaymentPeriod.MONTHLY,
						MealService.TWO_MEALS,
						RoomType.OTHER);
		AccommodationPayment pay11 = new AccommodationPayment(
						"4 literas dobles en una habitación muy muy espaciosa y con"
						+ " cocina y baños de la máxima calidad",
						149.99,
						PaymentPeriod.MONTHLY,
						MealService.SELF_CATERING,
						RoomType.OTHER);
		
		ac4.addPayment(pay10);
		ac4.addPayment(pay11);
		place13.setaccommodation(ac4);
		
		Accommodation ac5 = new Accommodation(
				850,
				new HashMap<>(),
				accommodationType.RESIDENCE);
		AccommodationPayment pay12 = new AccommodationPayment(
								"Media pensión (con opción de pensión completa), "
								+ "limpieza semanal incluida, baño propio, cocina "
								+ "equipada, escritorio, agua, climatización, WIFI, "
								+ "electricidad, televisión y todos los beneficios de "
								+ "micampus Club",
								615.1,
								PaymentPeriod.MONTHLY,
								MealService.TWO_MEALS,
								RoomType.DOUBLE
								);
		AccommodationPayment pay13 = new AccommodationPayment(
								"Media pensión (con opción de pensión completa), "
								+ "limpieza semanal incluida, baño propio, cocina "
								+ "equipada compartida, escritorio, agua, climatización, WIFI, "
								+ "electricidad, televisión y todos los beneficios de "
								+ "micampus Club",
								699.,
								PaymentPeriod.MONTHLY,
								MealService.TWO_MEALS,
								RoomType.INDIVIDUAL);
		ac5.addPayment(pay12);
		ac5.addPayment(pay13);
		place6.setaccommodation(ac5);
		
		return places;
	}
}
