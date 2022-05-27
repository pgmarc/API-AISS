package aiss.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import aiss.model.Event;


public class EventData {
	public static List<Event>getEventsInfo(){
		
		List<Event> events = new ArrayList<Event>();
		
		Event event1 = new Event();
		event1.setName("Noches de la Maestranza");
		event1.setContactEmail("aquilla@teatrodelamaestranza.es");
		event1.setDate(LocalDateTime.of(2022, 9, 17, 21, 30));
		event1.setDescription("Una noche llena de magia en La Maestranza con Plácido Domingo");
		event1.setOrganizers("La Maestranza");
		event1.setPrice(150.0);
		event1.setWebsite("https://www.entradas.com/event/placido-domingo-noches-de-la-maestranza-plaza-de-toros-de-la-maestranza-14621134/");
		
		Event event2 = new Event();
		event2.setName("Sergio Dalma");
		event2.setContactEmail("info.cite@eulen.com");
		event2.setDate(LocalDateTime.of(2022, 5, 21, 21, 0));
		event2.setDescription("Vuelve a disfrutar de los mayores exitos de Sergio Dalma");
		event2.setOrganizers("Cartuja Center");
		event2.setPrice(51.0);
		event2.setWebsite("https://www.sergiodalma.es/event/sevilla/");
		
		Event event3 = new Event();
		event3.setName("HAY QUE VIVIR EL MOMENTO ");
		event3.setContactEmail("info.cite@eulen.com");
		event3.setDate(LocalDateTime.of(2022, 6, 11, 22, 0));
		event3.setDescription("Tras dos obligados aplazamientos, Manuel Carrasco vuelve por fin, "
				+ " a la carretera con una gira renovada y con una clara premisa; “Hay que vivir el momento”.");
		event3.setOrganizers("Cartuja Center");
		event3.setPrice(30.0);
		event3.setWebsite("https://manuelcarrasco.es/eventos/");
		
		Event event4 = new Event();
		event4.setName("Melendi - Cabaret Festival");
		event4.setContactEmail("clubhipicosevilla@gmail.com");
		event4.setDate(LocalDateTime.of(2022, 9, 10, 21, 30));
		event4.setDescription("Escucha en directo “Likes y Cicatrices”, el undécimo álbum de Melendi, ya se ha convertido en todo un superventas");
		event4.setOrganizers("SIENTE LA MUSICA, AIE - V72389042");
		event4.setPrice(42.0);
		event4.setWebsite("https://www.elcorteingles.es/entradas/conciertos/entradas-melendi-cabaret-festival-mairena-del-aljarafe/");
		
		Event event5 = new Event();
		event5.setName("Camilo Tour España 2022");
		event5.setContactEmail("info.cite@eulen.com");
		event5.setDate(LocalDateTime.of(2022, 6, 18, 22, 00));
		event5.setDescription("CAMILO llega a España con su primer tour, Mis Manos, para deleitar a su tribu.");
		event5.setOrganizers("Cartuja Center");
		event5.setPrice(61.0);
		event5.setWebsite("https://www.vayaentradas.com/ciclos/ficha/557-camilo-tour-espana-2022");
		
		Event event6 = new Event();
		event6.setName("El paisaje andaluz. Por cañadas, cordeles y veredas");
		event6.setContactEmail("comunicacion@sevilla.org");
		event6.setDate(LocalDateTime.of(2022, 6, 5, 11, 00));
		event6.setDescription("Es una muestra colectiva que surge del proyecto creado en "
				+ "el año 2010 por el abogado y coleccionista Luis Venegas Lagüéns y el"
				+ " pintor Ricardo Casstillo");
		event6.setOrganizers("Ricardo Casstillo");
		event6.setPrice(3.0);
		event6.setWebsite("https://icas.sevilla.org/espacios/fabiola/agenda/el-paisaje-andaluz-por-canadas-cordeles-y-veredas");
		
		Event event7 = new Event();
		event7.setName("La imagen humana. Arte, identidades y simbolismo");
		event7.setContactEmail("redessociales@guiadelocio.com");
		event7.setDate(LocalDateTime.of(2022, 5, 29, 10, 0));
		event7.setDescription("Un viaje a través del tiempo y de las culturas");
		event7.setOrganizers("CaixaForum Sevilla");
		event7.setPrice(6.0);
		event7.setWebsite("https://www.guiadelocio.com/sevilla/arte/sevilla/la-imagen-humana-arte-identidades-y-simbolismo#localizacion");
		
		Event event8 = new Event();
		event8.setName("Ser = Espacio x Acción. Una fábula especulativa");
		event8.setContactEmail("comunicacion@sevilla.org");
		event8.setDate(LocalDateTime.of(2022, 7, 3, 11, 0));
		event8.setDescription("La exposición reune cincuenta obras del artista "
				+ "sevillano Federico Guzmán entre las que se incluyen obras gráficas "
				+ "y digitales además de pinturas");
		event8.setOrganizers("Federico Guzmán");
		event8.setPrice(0.0);
		event8.setWebsite("https://icas.sevilla.org/espacios/espacio-turina");
		
		Event event9 = new Event();
		event9.setName("Permutaciones / Probabilidades / Azar");
		event9.setContactEmail("cicus0@us.es");
		event9.setDate(LocalDateTime.of(2022, 7, 22, 11, 0));
		event9.setDescription("Es una exposición individual en la que se exhiben "
				+ "lienzos, dibujos, vídeos, fotografías y piezas escultóricas de la "
				+ "artista Esther Ferrer ");
		event9.setOrganizers("Esther Ferrer ");
		event9.setPrice(0.0);
		event9.setWebsite("https://cicus.us.es/");
		
		Event event10 = new Event();
		event10.setName("Rubén Guerrero");
		event10.setContactEmail("actividades.caac@juntadeandalucia.es");
		event10.setDate(LocalDateTime.of(2022, 9, 11, 11, 0));
		event10.setDescription("Exposición individual dedicada al artista sevillano Rubén Guerrero, "
				+ "nacido en Utrera en el año 1976");
		event10.setOrganizers(null);
		event10.setPrice(2.0);
		event10.setWebsite("http://www.caac.es/");
		
		Event event11 = new Event();
		event11.setName("Flamenco Viene del Sur");
		event11.setContactEmail("institutoandaluz.flamenco@juntadeandalucia.es");
		event11.setDate(LocalDateTime.of(2022, 6, 2, 21, 0));
		event11.setDescription("Todo tipo de experiencias -sonoras, escénicas- "
				+ "que permiten evolucionar al flamenco");
		event11.setOrganizers("Junta de Andalucía");
		event11.setPrice(18.0);
		event11.setWebsite("https://www.juntadeandalucia.es/cultura/flamenco/content/flamenco-viene-del-sur");
		
		Event event12 = new Event();
		event12.setName("Visitas guiadas a la sede de la Fundación Tres Culturas");
		event12.setContactEmail("info@tresculturas.org");
		event12.setDate(LocalDateTime.of(2022, 5, 23, 21, 30));
		event12.setDescription("El recorrido tiene una duración aproximada de una hora, en la que los visitantes "
				+ "recorren tanto el exterior como el interior del pabellón");
		event12.setOrganizers("Fundación Tres Culturas del Mediterráneo");
		event12.setPrice(0.0);
		event12.setWebsite("https://www.eventbrite.es/e/entradas-programa-de-visitas-guiadas-a-la-sede-de-la-fundacion-tres-culturas-327927749727");
		
		Event event13 = new Event();
		event13.setName("Catedral y Giralda de Sevilla");
		event13.setContactEmail("reservaspatrimonio@catedraldesevilla.es");
		event13.setDate(LocalDateTime.of(2022, 8, 18, 18, 0));
		event13.setDescription("La Catedral de Sevilla le propone hoy una exclusiva visita guiada "
				+ "por los espacios más significativo de su interior para revelar su historia y su mensaje evangelizador.");
		event13.setOrganizers("Ayuntamiento de Sevilla");
		event13.setPrice(21.0);
		event13.setWebsite("https://onsevilla.com/catedral-giralda-sevilla#:~:text=Horario%3A%20lunes%20a%20s%C3%A1bados%20de,domingos%20a%20las%2017%20horas");
		
		Event event14 = new Event();
		event14.setName("Visita Torre del Oro");
		event14.setContactEmail("torreoro@fn.mde.es");
		event14.setDate(LocalDateTime.of(2022, 10, 3, 19, 0));
		event14.setDescription("La Torre del Oro es sin duda uno de los lugares imprescindibles de "
				+ "Sevilla y por tanto de visita obligada.");
		event14.setOrganizers("Junta de Andalucía");
		event14.setPrice(3.0);
		event14.setWebsite("https://www.juntadeandalucia.es/cultura/agendaculturaldeandalucia/evento/torre-del-oro-visitas");
		
		Event event15 = new Event();
		event15.setName("Las joyas del mudejar en Sevilla");
		event15.setContactEmail("info@engranajesculturales.com");
		event15.setDate(LocalDateTime.of(2022, 5, 28, 11, 0));
		event15.setDescription("Engranajes Culturales lanza una ruta urbana, "
				+ "visitando el interior de algunos de los templos, para conocer las joyas del mudéjar sevillano");
		event15.setOrganizers("Engranajes Culturales");
		event15.setPrice(14.0);
		event15.setWebsite("www.engranajesculturales.com");
		
		Event event16 = new Event();
		event16.setName("EL PRINCIPITO");
		event16.setContactEmail("infoteatro@tavorateatroabierto.com");
		event16.setDate(LocalDateTime.of(2022, 6, 18, 12, 0));
		event16.setDescription("TALLER DE FIN DE CURSO DE LA ESCUELA "
				+ "DE TEATRO DAMAS DE COMEDIA DIRIGIDA POR NIEVES ZAMORA Y ALICIA MORENO");
		event16.setOrganizers("Távora Teatro Abierto");
		event16.setPrice(9.0);
		event16.setWebsite("https://tavorateatroabierto.com");
		
		
		Event event17 = new Event();
		event17.setName("EL TIEMPO ENTRE COSTURAS – EL MUSICAL");
		event17.setContactEmail("info.cite@eulen.com");
		event17.setDate(LocalDateTime.of(2022, 5, 29, 17, 0));
		event17.setDescription("La joven modista Sira Quiroga abandona Madrid en los meses "
				+ "previos al inicio de la guerra civil arrastrada por el amor desbocado hacia un hombre a quien apenas conoce. ");
		event17.setOrganizers("Cartuja Center");
		event17.setPrice(59.0);
		event17.setWebsite("https://cartujacenter.janto.es/espectaculo/el-tiempo-entre-costuras-el-musical/COSTURAS");
		
		Event event18 = new Event();
		event18.setName("Buenismo Show en Sevilla");
		event18.setContactEmail("info@yventu.com");
		event18.setDate(LocalDateTime.of(2022, 11, 18, 21, 0));
		event18.setDescription("Confesiones polandiacuteticos "
				+ "corruptos y divas del pop Manuel Burque Henar andAacutelvarez "
				+ "y Quique Peinado estrenan Buenismo show un espectandaacuteculo basado en "
				+ "el programa de radio que presentan y en el que los chistes disparan la rabia parriba Segur");
		event18.setOrganizers("GUADALMEDIA EVENTS, S.L.");
		event18.setPrice(20.0);
		event18.setWebsite("https://www.elcorteingles.es/entradas/teatro/entradas-buenismo-show-sevilla/");
		
		Event event19 = new Event();
		event19.setName("Jose Corbacho Ante Todo Mucha Calma en Sevilla");
		event19.setContactEmail("info@lamascaraproducciones.es");
		event19.setDate(LocalDateTime.of(2022, 10, 29, 21, 30));
		event19.setDescription("Entradas a la venta para el espectáculo cómico de José Corbacho,"
				+ "Ante Todo Mucha Calma que tendrá lugar el 29 de octubre de 2022 en el Teatro Los Remedios de Sevilla.");
		event19.setOrganizers("La Máscara Producciones SL");
		event19.setPrice((double)24);
		event19.setWebsite("https://www.elcorteingles.es/entradas/teatro/entradas-jose-corbacho-ante-todo-mucha-calma-sevilla/?performance_url=entradas-jose-corbacho-ante-todo-mucha-calma-sevilla/&awc=13034_1653257513_be177e92927ac92122b00888a81bbe8a");
		
		Event event20 = new Event();
		event20.setName("Cómo Conocí a mi Suegra en Sevilla");
		event20.setContactEmail("info@lamascaraproducciones.es");
		event20.setDate(LocalDateTime.of(2022, 12, 10, 19, 45));
		event20.setDescription("sucesión de monólogos cotidianos donde realizan una divertida parodia centrándose en la relación de un pareja y "
				+ "en situaciones del día a día analizadas desde el punto de vista humorístico.");
		event20.setOrganizers("GUADALMEDIA EVENTS, S.L");
		event20.setPrice((double)16);
		event20.setWebsite("https://www.elcorteingles.es/entradas/teatro/entradas-como-conoci-a-mi-suegra-sevilla/?performance_url=entradas-como-conoci-a-mi-suegra-sevilla/");
		
		events.add(event1);
		events.add(event2);
		events.add(event3);
		events.add(event4);
		events.add(event5);
		events.add(event6);
		events.add(event7);
		events.add(event8);
		events.add(event9);
		events.add(event10);
		events.add(event11);
		events.add(event12);
		events.add(event13);
		events.add(event14);
		events.add(event15);
		events.add(event16);
		events.add(event17);
		events.add(event18);
		events.add(event19);
		events.add(event20);
		
		
		return events;
		
	}
}
