package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library l;
    Playlist p;
    Playlist p2;
    Song s1;
    Song s2;
    Song s3;

    @BeforeEach
    void runBefore(){
        l = new Library("JordanW");
        p = new Playlist("Hip-Hop","Rap");
        p2 = new Playlist("Yeehaw", "Country");
        s1 = new Song("Monster","Kanye West","Rap",200);
        s2 = new Song("Space Cowboy","Kasey Musgraves","Country", 250);
        s3 = new Song("Bonfire","Childish Gambino","Rap", 300);
    }

    @Test
    void testConstructorLibrary(){
        assertEquals("JordanW",l.getUsername());
        assertEquals("JordanW's Library",l.getLibrary());
        assertNotNull(l);
    }

    @Test
    void TestAddPlaylist(){
        l.addPlaylist(p);
        l.addPlaylist(p2);
        assertEquals(2,l.getSize());
    }

    @Test
    void testRemovePlaylist(){
        l.addPlaylist(p);
        l.addPlaylist(p2);
        assertEquals(2,l.getSize());
        l.removePlaylist(p);
        assertEquals(1,l.getSize());
    }

    @Test
    void toStringLibrary(){
        p.addSong(s1);
        p.addSong(s2);
        p2.addSong(s3);
        assertEquals("JordanW's Playlist",l.viewPlaylists("JordanW"));
    }
}
