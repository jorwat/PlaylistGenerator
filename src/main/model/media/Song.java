package model.media;

// Represents a song having a title, artist genre, and runtime in seconds
public class Song {
    private String title;
    private String artist;
    private String genre;
    private int runtime;

    // EFFECTS: constructs a song item with a title, artist, genre, and runtime in seconds
    public Song(String title, String artist, String genre, int time) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        runtime = time;
    }

    // EFFECTS: returns title of the song
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns artist of the song
    public String getArtist() {
        return artist;
    }

    // EFFECTS: returns genre of the song
    public String getGenre() {
        return genre;
    }

    // EFFECTS: returns runtime of the song
    public int getRuntime() {
        return runtime;
    }

    // EFFECTS: returns description of song including title, artist, genre, and runtime
    public String toStringSong() {
        return getTitle() + " by " + getArtist() + ": " + getGenre();
    }

}
