package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Song class
class SongTest extends LibraryTest{

    @Test
    void testConstructorSong(){
        assertEquals("Monster",s1.getTitle());
        assertEquals("Kanye West",s1.getArtist());
        assertEquals("Rap",s1.getGenre());
        assertEquals(200,s1.getRuntime());
    }

    @Test
    void testToStringSong(){
        assertEquals("Monster by Kanye West: Rap",s1.toStringSong());
    }

}