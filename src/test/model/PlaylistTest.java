package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit Tests for Playlist Class
public class PlaylistTest extends LibraryTest{

    @Test
    void testConstructorPlaylist(){
        assertEquals("Hip-Hop",p.getPlaylistName());
        assertEquals("Rap",p.getPlaylistGenre());
        assertEquals(0,p.getTotalSongs());
        assertEquals(0,p.getTotalRuntime());
    }

    @Test
    void TestAddSong(){
        p.addSong(s1);
        p.addSong(s2);
        p.addSong(s3);
        assertEquals(3,p.getTotalSongs());
        assertEquals(750,p.getTotalRuntime());
    }

    @Test
    void TestRemoveSong(){
        p.addSong(s1);
        p.addSong(s2);
        p.addSong(s3);
        assertEquals(3,p.getTotalSongs());
        assertEquals(750,p.getTotalRuntime());
        p.removeSong(s3);
        assertEquals(2,p.getTotalSongs());
        assertEquals(450,p.getTotalRuntime());
    }

    @Test
    void toStringPlaylist(){
        p.addSong(s1);
        p.addSong(s2);
        p.addSong(s3);
        assertEquals("Hip-Hop!: a Rap type playlist\n" +
                "Monster by Kanye West: Rap\n" +
                "Space Cowboy by Kasey Musgraves: Country\n" +
                "Bonfire by Childish Gambino: Rap",p.toStringPlaylist());
    }
}
