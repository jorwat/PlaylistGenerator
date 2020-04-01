package model;

import exceptions.NoPlaylistException;
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
        l.addPlaylist(p);
        l.addPlaylist(p2);
        assertEquals("\nHip-Hop: a Rap playlist with 2 song/songs running at 450 seconds!\n" +
                "Yeehaw: a Country playlist with 1 song/songs running at 300 seconds!",l.viewPlaylists());
    }

    @Test
    void testMatchAndAddWithValidPlaylist() {
        l.addPlaylist(p);
        try {
            l.matchAndAdd(p.getPlaylistName(),s1);
        } catch (NoPlaylistException e){
            fail("No Exception thrown");
        }
    }

    @Test
    void testMatchAndAddWithNonValidPlaylist() {
        l.addPlaylist(p);
        try {
            l.matchAndAdd("My Playlist", s2);
            fail("Throw Exception");
        } catch (NoPlaylistException e) {
            //test passed
        }
    }

    @Test
    void testMatchAndViewRuntimeWithValidPlaylist() {
        p.addSong(s1);
        l.addPlaylist(p);
        try {
            assertEquals(200,l.matchAndFindRuntime(p.getPlaylistName()));
        } catch (Exception e) {
            fail("No Exception thrown");
        }
    }

    @Test
    void testMatchAndViewRuntimeWithNonValidPlaylist() {
        p.addSong(s1);
        l.addPlaylist(p);
        try {
            assertEquals(350,l.matchAndFindRuntime("My Playlist"));
            fail("No Exception thrown");
        } catch (NoPlaylistException e) {
            // test passed
        }
    }

    @Test
    void testMatchAndViewSongsWithValidPlaylist() {
        p.addSong(s1);
        l.addPlaylist(p);
        try {
            assertEquals("Hip-Hop!: a Rap type playlist\n" +
                    "Monster by Kanye West: Rap",l.matchAndViewSongs(p.getPlaylistName()));
        } catch (NoPlaylistException e) {
            fail("Exception not thrown");
        }
    }

    @Test
    void testMatchAndViewSongsWithNonValidPlaylsit() {
        p.addSong(s1);
        l.addPlaylist(p);
        try {
            assertEquals("",l.matchAndViewSongs("My Playlist"));
            fail("No Exception thrown");
        } catch (NoPlaylistException e) {
            // test passed
        }
    }

}
