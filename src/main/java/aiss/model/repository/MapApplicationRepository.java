package aiss.model.repository;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

import aiss.model.Event;
import aiss.model.Playlist;
import aiss.model.Song;
import aiss.model.Event;


<<<<<<< HEAD
public class MapApplicationRepository implements PlaylistRepository, EventRepository{

	Map<String, Playlist> playlistMap;
	Map<String, Song> songMap;
	Map<Integer, Event> eventMap;
=======
public class MapApplicationRepository implements PlaylistRepository, EventsRepository{

	Map<String, Playlist> playlistMap;
	Map<String, Song> songMap;
	Map<String, Event>eventsMap;
>>>>>>> ad1a52eebfc0339091be284fa47f6e5e45ade2dd
	private static MapApplicationRepository instance=null;
	private int index=0;			// Index to create playlists and songs' identifiers.
	
	
	public static MapApplicationRepository getInstance() {
		
		if (instance==null) {
			instance = new MapApplicationRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		playlistMap = new HashMap<String,Playlist>();
		songMap = new HashMap<String,Song>();
<<<<<<< HEAD
		eventMap = new HashMap<Integer,Event>();
=======
		eventsMap = new HashMap<String, Event>();
>>>>>>> ad1a52eebfc0339091be284fa47f6e5e45ade2dd
		
		// Create songs
		Song rollingInTheDeep=new Song();
		rollingInTheDeep.setTitle("Rolling in the Deep");
		rollingInTheDeep.setArtist("Adele");
		rollingInTheDeep.setYear("2011");
		rollingInTheDeep.setAlbum("21");
		addSong(rollingInTheDeep);
		
		Song one=new Song();
		one.setTitle("One");
		one.setArtist("U2");
		one.setYear("1992");
		one.setAlbum("Achtung Baby");
		addSong(one);
		
		Song losingMyReligion=new Song();
		losingMyReligion.setTitle("Losing my Religion");
		losingMyReligion.setArtist("REM");
		losingMyReligion.setYear("1991");
		losingMyReligion.setAlbum("Out of Time");
		addSong(losingMyReligion);
		
		Song smellLikeTeenSpirit=new Song();
		smellLikeTeenSpirit.setTitle("Smell Like Teen Spirit");
		smellLikeTeenSpirit.setArtist("Nirvana");
		smellLikeTeenSpirit.setAlbum("Nevermind");
		smellLikeTeenSpirit.setYear("1991");
		addSong(smellLikeTeenSpirit);
		
		Song gotye=new Song();
		gotye.setTitle("Someone that I used to know");
		gotye.setArtist("Gotye");
		gotye.setYear("2011");
		gotye.setAlbum("Making Mirrors");
		addSong(gotye);
		
		// Create playlists
		Playlist japlaylist=new Playlist();
		japlaylist.setName("AISSPlayList");
		japlaylist.setDescription("AISS PlayList");
		addPlaylist(japlaylist);
		
		Playlist playlist = new Playlist();
		playlist.setName("Favourites");
		playlist.setDescription("A sample playlist");
		addPlaylist(playlist);
		
		// Add songs to playlists
		addSong(japlaylist.getId(), rollingInTheDeep.getId());
		addSong(japlaylist.getId(), one.getId());
		addSong(japlaylist.getId(), smellLikeTeenSpirit.getId());
		addSong(japlaylist.getId(), losingMyReligion.getId());
		
		addSong(playlist.getId(), losingMyReligion.getId());
		addSong(playlist.getId(), gotye.getId());
	}
	
	// Playlist related operations
	@Override
	public void addPlaylist(Playlist p) {
		String id = "p" + index++;	
		p.setId(id);
		playlistMap.put(id,p);
	}
	
	@Override
	public Collection<Playlist> getAllPlaylists() {
			return playlistMap.values();
	}

	@Override
	public Playlist getPlaylist(String id) {
		return playlistMap.get(id);
	}
	
	@Override
	public void updatePlaylist(Playlist p) {
		playlistMap.put(p.getId(),p);
	}

	@Override
	public void deletePlaylist(String id) {	
		playlistMap.remove(id);
	}
	

	@Override
	public void addSong(String playlistId, String songId) {
		Playlist playlist = getPlaylist(playlistId);
		playlist.addSong(songMap.get(songId));
	}

	@Override
	public Collection<Song> getAll(String playlistId) {
		return getPlaylist(playlistId).getSongs();
	}

	@Override
	public void removeSong(String playlistId, String songId) {
		getPlaylist(playlistId).deleteSong(songId);
	}

	
	// Song related operations
	
	@Override
	public void addSong(Song s) {
		String id = "s" + index++;
		s.setId(id);
		songMap.put(id, s);
	}
	
	@Override
	public Collection<Song> getAllSongs() {
			return songMap.values();
	}

	@Override
	public Song getSong(String songId) {
		return songMap.get(songId);
	}

	@Override
	public void updateSong(Song s) {
		Song song = songMap.get(s.getId());
		song.setTitle(s.getTitle());
		song.setAlbum(s.getAlbum());
		song.setArtist(s.getArtist());
		song.setYear(s.getYear());
	}

	@Override
	public void deleteSong(String songId) {
		songMap.remove(songId);
	}

	@Override
<<<<<<< HEAD
	public void addEvent(Event e) {
		Integer id = index++;	
		e.setIndex(id);
		eventMap.put(id,e);
=======
	public void addEvent(Event event) {
		String id = "event" + index++;	
		event.setId(id);
		eventsMap.put(id,event);
>>>>>>> ad1a52eebfc0339091be284fa47f6e5e45ade2dd
		
	}

	@Override
	public Collection<Event> getAllEvents() {
<<<<<<< HEAD
		return eventMap.values();
	
	}


	@Override
	public Event getEvent(Integer eventId) {
		return eventMap.get(eventId);
	}

	@Override
	public void updateEvent(Event e) {
		eventMap.put(e.getIndex(),e);
=======
		return eventsMap.values();
	}

	@Override
	public Event getEvents(Integer eventId) {
		return eventsMap.get(eventId);
	}

	@Override
	public void updateEvent(Event event) {
		Event e = eventsMap.get(event.getId());
		e.setName(e.getName());
		e.setAlbum(e.getDescription());
		e.setArtist(e.getDate());
		e.setYear(e.getPrice());
>>>>>>> ad1a52eebfc0339091be284fa47f6e5e45ade2dd
		
	}

	@Override
	public void deleteEvent(Integer eventId) {
<<<<<<< HEAD
		eventMap.remove(eventId);
=======
		eventsMap.remove(eventId);// TODO Auto-generated method stub
>>>>>>> ad1a52eebfc0339091be284fa47f6e5e45ade2dd
		
	}
	
}
